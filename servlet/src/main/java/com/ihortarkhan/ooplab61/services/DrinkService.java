package com.ihortarkhan.ooplab61.services;

import com.ihortarkhan.ooplab61.dto.response.DrinkResponse;
import com.ihortarkhan.ooplab61.repositories.DrinkRepository;
import lombok.SneakyThrows;

import java.util.List;

public class DrinkService {
    private final DrinkRepository drinkRepository = new DrinkRepository();

    public List<DrinkResponse> findAll() {
        return drinkRepository.findAll().stream()
                .map(entity ->
                        DrinkResponse.builder()
                                .id(entity.getId())
                                .name(entity.getName())
                                .price(entity.getPrice())
                                .count(entity.getCount())
                                .build())
                .toList();
    }
}
