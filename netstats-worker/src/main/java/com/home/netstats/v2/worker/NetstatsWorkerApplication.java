package com.home.netstats.v2.worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the worker application.
 * <p>
 * Ingests data from target network devices and runs the analysis
 * (core ETL / worker logic).
 */
@SpringBootApplication
public class NetstatsWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetstatsWorkerApplication.class, args);
    }
}