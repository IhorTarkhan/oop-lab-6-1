package com.ihortarkhan.ooplab61.repositories;

import com.ihortarkhan.ooplab61.entities.CoffeeAdminEntity;
import com.ihortarkhan.ooplab61.exceptions.HttpException;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;

public class CoffeeAdminRepository {
    private final RepositoryUtil repositoryUtil = new RepositoryUtil();

    @SneakyThrows
    public CoffeeAdminRepository() {
        Class.forName("org.postgresql.Driver");
        repositoryUtil.openStatement().get().execute("""
                CREATE TABLE IF NOT EXISTS coffee_admin
                (
                    id       serial PRIMARY KEY,
                    username text
                );
                """);
    }

    @SneakyThrows
    public CoffeeAdminEntity findByUsername(String username) {
        try (ResultSet resultSet = repositoryUtil.openStatement().get()
                .executeQuery("SELECT * FROM coffee_admin WHERE username = '%s';".formatted(username))) {
            if (resultSet.getFetchSize() != 1) {
                throw new HttpException(HttpServletResponse.SC_NOT_FOUND);
            }
            resultSet.next();
            return CoffeeAdminEntity.builder()
                    .id(resultSet.getLong("id"))
                    .username(resultSet.getString("username"))
                    .build();
        }
    }
}
