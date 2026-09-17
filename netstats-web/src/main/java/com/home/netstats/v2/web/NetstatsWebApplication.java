package com.home.netstats.v2.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the web application.
 * <p>
 * Provides the UI to query data and update devices' profile information
 * (username, password, etc.) used by the workers.
 */
@SpringBootApplication
public class NetstatsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetstatsWebApplication.class, args);
    }
}