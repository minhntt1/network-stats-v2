package com.home.netstats.v2.common.config;

import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.testcontainers.containers.MySQLContainer;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Test DB configuration for {@code netstats-common} integration tests.
 *
 * <p>
 * Mirrors the example {@code Config.java} (Hikari DataSource + JDBC transaction
 * manager + MyBatis {@code SqlSessionFactory}) but sources every constant from
 * {@code db.properties} and spins up a real MySQL via Testcontainers.
 * </p>
 *
 * <p>
 * The schema ({@code db/schema.sql}) and dummy data ({@code db/data.sql}) are
 * applied through plain JDBC after container startup so they stay in separate
 * files and avoid Testcontainers' init-script copying logic.
 * </p>
 */
@Configuration
@MapperScan(basePackages = "com.home.netstats.v2.common")
public class TestDbConfig {

    private static final Properties PROPS = loadProperties();

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream in = TestDbConfig.class.getResourceAsStream("/db.properties")) {
            props.load(in);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot load /db.properties", e);
        }
        return props;
    }

    /**
     * Single shared MySQL container, created once per JVM. Schema and data are
     * applied through JDBC after startup in {@link #ensureStarted()}.
     */
    private static final MySQLContainer<?> CONTAINER = createContainer();

    private static MySQLContainer<?> createContainer() {
        MySQLContainer<?> container = new MySQLContainer<>(PROPS.getProperty("db.container.image"))
                .withDatabaseName(PROPS.getProperty("db.container.database"))
                .withUsername(PROPS.getProperty("db.runner.username"))
                .withPassword(PROPS.getProperty("db.runner.password"));
        return container;
    }

    /**
     * Start the container (idempotent), then apply schema and dummy data through
     * plain JDBC so they stay in separate files and we avoid Testcontainers'
     * init-script copying logic.
     */
    static void ensureStarted() {
        if (!CONTAINER.isRunning()) {
            CONTAINER.start();
            applyScript(PROPS.getProperty("db.container.init.schema"));
            applyScript(PROPS.getProperty("db.container.init.data"));
        }
    }

    private static void applyScript(String script) {
        var resource = new org.springframework.core.io.ClassPathResource(script);
        try (var conn = CONTAINER.createConnection("")) {
            org.springframework.jdbc.datasource.init.ScriptUtils.executeSqlScript(conn, resource);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot apply script " + script, e);
        }
    }

    @Bean
    DataSource dataSource() {
        ensureStarted();
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(PROPS.getProperty("db.driver.class"));
        hikariConfig.setJdbcUrl(CONTAINER.getJdbcUrl() + "?" + PROPS.getProperty("db.jdbc.params"));
        hikariConfig.setUsername(CONTAINER.getUsername());
        hikariConfig.setPassword(CONTAINER.getPassword());
        hikariConfig.setAutoCommit(Boolean.parseBoolean(PROPS.getProperty("db.pool.auto.commit")));
        // Hikari transaction isolation string, e.g. TRANSACTION_READ_COMMITTED
        hikariConfig.setTransactionIsolation(PROPS.getProperty("db.pool.transaction.isolation"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(PROPS.getProperty("db.pool.maximum.pool.size")));
        hikariConfig.setConnectionTimeout(Long.parseLong(PROPS.getProperty("db.pool.connection.timeout.millis")));
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }

    /**
     * MyBatis session factory bound to the test {@code DataSource}; scans the
     * mapper XMLs from the classpath.
     */
    @Bean
    SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        var config = new SqlSessionFactoryBean();

        // map DB snake_case columns (data_class, temp_data, ...) to camelCase model fields
        var mybatisConfig = new org.apache.ibatis.session.Configuration();
        mybatisConfig.setMapUnderscoreToCamelCase(true);
        config.setConfiguration(mybatisConfig);

        config.setTransactionFactory(new SpringManagedTransactionFactory());
        config.setDataSource(dataSource);
        config.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(PROPS.getProperty("mybatis.mapper.locations")));
        return config.getObject();
    }
}