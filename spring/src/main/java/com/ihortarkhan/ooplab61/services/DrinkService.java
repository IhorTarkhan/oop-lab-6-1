package com.ihortarkhan.ooplab61.services;

import com.ihortarkhan.ooplab61.dto.response.DrinkResponse;
import com.ihortarkhan.ooplab61.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrinkService {
    private final DrinkRepository drinkRepository;

    public List<DrinkResponse> findAll() {
        return drinkRepository.findByOrderById().stream()
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
