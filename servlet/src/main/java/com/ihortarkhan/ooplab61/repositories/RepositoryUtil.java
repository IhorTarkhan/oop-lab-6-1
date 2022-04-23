package com.ihortarkhan.ooplab61.repositories;

import com.ihortarkhan.ooplab61.configurations.PropertiesConfiguration;
import com.ihortarkhan.ooplab61.entities.CoffeeUserEntity;
import com.ihortarkhan.ooplab61.exceptions.HttpException;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.function.Supplier;

public class RepositoryUtil {
    public Supplier<Statement> openStatement() {
        return () -> {
            try (Connection connection =
                         DriverManager.getConnection(
                                 PropertiesConfiguration.getProperties().getDatabaseUrl(),
                                 PropertiesConfiguration.getProperties().getDatabaseUsername(),
                                 PropertiesConfiguration.getProperties().getDatabasePassword())) {
                try (Statement statement = connection.createStatement()) {
                    return statement;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
