package com.home.netstats.v2.common.config;

import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base for integration tests that need a real MySQL (Testcontainers) with the
 * MyBatis {@code SqlSessionFactory} and mappers wired up.
 *
 * <p>
 * Each test is transactional and rolled back so mutations never leak between
 * tests or pollute the shared container's seed data.
 * </p>
 */
@SpringJUnitConfig(TestDbConfig.class)
@Transactional
public abstract class AbstractCommonDbTest {
}