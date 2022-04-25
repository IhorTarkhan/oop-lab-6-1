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
        repositoryUtil.runOnStatement(s -> s.execute("""
                CREATE TABLE IF NOT EXISTS coffee_user
                (
                    id       serial PRIMARY KEY,
                    username text,
                    amount   numeric
                );
                """));
    }

    @SneakyThrows
    public CoffeeUserEntity findByUsername(String username) {
        return repositoryUtil.runOnStatement(s -> {
            ResultSet resultSet = s.executeQuery("SELECT * FROM coffee_user WHERE username = '%s';"
                    .formatted(username));
            if (!resultSet.next()) {
                throw new HttpException(HttpServletResponse.SC_NOT_FOUND);
            }
            return CoffeeUserEntity.builder()
                    .id(resultSet.getLong("id"))
                    .username(resultSet.getString("username"))
                    .amount(resultSet.getLong("amount"))
                    .build();
        });
    }

    @SneakyThrows
    public void updateAmount(Long id, Long nuwAmount) {
        repositoryUtil.runOnStatement(s -> s.execute(
                "UPDATE coffee_user SET amount = %s WHERE id = %s;"
                        .formatted(nuwAmount, id)));
    }
}
