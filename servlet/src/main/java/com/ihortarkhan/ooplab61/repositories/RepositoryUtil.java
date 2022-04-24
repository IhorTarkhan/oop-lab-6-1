package com.ihortarkhan.ooplab61.repositories;

import com.ihortarkhan.ooplab61.configurations.PropertiesConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RepositoryUtil {
    public <R> R runOnStatement(MyFunction<Statement, R> consumer) {
        try (Connection connection =
                     DriverManager.getConnection(
                             PropertiesConfiguration.getProperties().getDatabaseUrl(),
                             PropertiesConfiguration.getProperties().getDatabaseUsername(),
                             PropertiesConfiguration.getProperties().getDatabasePassword())) {
            try (Statement statement = connection.createStatement()) {
                return consumer.accept(statement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    public interface MyFunction<T, R> {
        R accept(T t) throws SQLException;
    }
}
