package com.ihortarkhan.ooplab61.repositories;

import com.ihortarkhan.ooplab61.entities.CoffeeUserEntity;
import com.ihortarkhan.ooplab61.exceptions.HttpException;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;

public class CoffeeUserRepository {
    private final RepositoryUtil repositoryUtil = new RepositoryUtil();

    @SneakyThrows
    public CoffeeUserRepository() {
        Class.forName("org.postgresql.Driver");
        repositoryUtil.openStatement().get().execute("""
                CREATE TABLE IF NOT EXISTS coffee_user
                (
                    id       serial PRIMARY KEY,
                    username text,
                    amount   numeric
                );
                """);
    }

    @SneakyThrows
    public CoffeeUserEntity findByUsername(String username) {
        try (ResultSet resultSet = repositoryUtil.openStatement().get()
                .executeQuery("SELECT * FROM coffee_user WHERE username = '%s';".formatted(username))) {
            if (resultSet.getFetchSize() != 1) {
                throw new HttpException(HttpServletResponse.SC_NOT_FOUND);
            }
            resultSet.next();
            return CoffeeUserEntity.builder()
                    .id(resultSet.getLong("id"))
                    .username(resultSet.getString("username"))
                    .amount(resultSet.getLong("amount"))
                    .build();
        }
    }
}
