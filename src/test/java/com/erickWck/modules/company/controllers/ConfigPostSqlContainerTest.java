package com.erickWck.modules.company.controllers;

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
                    .withPassword("password");


    @Test
    void connectionEstablished() {
        assertThat(postgreSQLContainer.isCreated()).isTrue();
        assertThat(postgreSQLContainer.isRunning()).isTrue();

        // Exibindo informações de conexão
        System.out.println("DB URL: " + postgreSQLContainer.getJdbcUrl());
        System.out.println("DB Name: " + postgreSQLContainer.getDatabaseName());
        System.out.println("DB Username: " + postgreSQLContainer.getUsername());
        System.out.println("DB Password: " + postgreSQLContainer.getPassword());
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
