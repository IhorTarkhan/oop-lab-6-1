package com.ihortarkhan.ooplab61.repositories;

import com.ihortarkhan.ooplab61.entities.DrinkEntity;
import com.ihortarkhan.ooplab61.exceptions.HttpException;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DrinkRepository {
    private final RepositoryUtil repositoryUtil = new RepositoryUtil();

    @SneakyThrows
    public DrinkRepository() {
        Class.forName("org.postgresql.Driver");
        repositoryUtil.openStatement().get().execute("""
                CREATE TABLE IF NOT EXISTS drink
                (
                    id    serial PRIMARY KEY,
                    name  text,
                    price numeric,
                    count numeric
                );
                """);
    }

    @SneakyThrows
    public List<DrinkEntity> findAll() {
        try (ResultSet resultSet = repositoryUtil.openStatement().get()
                .executeQuery("SELECT * FROM drink;")) {
            ArrayList<DrinkEntity> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(DrinkEntity.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .price(resultSet.getLong("price"))
                        .count(resultSet.getLong("count"))
                        .build());
            }
            return result;
        }
    }

    @SneakyThrows
    public DrinkEntity findById(Long id) {
        try (ResultSet resultSet = repositoryUtil.openStatement().get()
                .executeQuery("SELECT * FROM drink WHERE id = '%s';".formatted(id))) {
            if (resultSet.getFetchSize() != 1) {
                throw new HttpException(HttpServletResponse.SC_NOT_FOUND);
            }
            resultSet.next();
            return DrinkEntity.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .price(resultSet.getLong("price"))
                    .count(resultSet.getLong("count"))
                    .build();
        }
    }

    @SneakyThrows
    public void updateCount(Long id, Long nuwCount) {
        repositoryUtil.openStatement().get()
                .executeQuery(
                        "UPDATE drink SET count = %s WHERE id = %s;"
                                .formatted(nuwCount, id));
    }
}
