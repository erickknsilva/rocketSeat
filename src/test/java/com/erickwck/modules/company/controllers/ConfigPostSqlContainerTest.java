package com.erickwck.modules.company.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
public class ConfigPostSqlContainerTest {


    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:latest")
                    .withDatabaseName("testdb")
                    .withUsername("postgres")
                    .withPassword("password")
                    .withReuse(true);


    @Test
    void connectionEstablished() {
        assertThat(postgreSQLContainer.isCreated()).isTrue();
        assertThat(postgreSQLContainer.isRunning()).isTrue();
    }

    public static String getJdbcUrl() {
        return postgreSQLContainer.getJdbcUrl();
    }

    public static String getUsername() {
        return postgreSQLContainer.getUsername();
    }

    public static String getPassword() {
        return postgreSQLContainer.getPassword();
    }
}
