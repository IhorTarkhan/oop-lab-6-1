package com.ihortarkhan.ooplab61.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihortarkhan.ooplab61.dto.request.RefreshDrinkRequest;
import com.ihortarkhan.ooplab61.dto.response.CoffeeAdminResponse;
import com.ihortarkhan.ooplab61.entities.CoffeeAdminEntity;
import com.ihortarkhan.ooplab61.entities.DrinkEntity;
import com.ihortarkhan.ooplab61.repositories.CoffeeAdminRepository;
import com.ihortarkhan.ooplab61.repositories.DrinkRepository;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

public class CoffeeAdminService {
    private final AuthorizationService authorizationService = new AuthorizationService();
    private final CoffeeAdminRepository coffeeAdminRepository = new CoffeeAdminRepository();
    private final DrinkRepository drinkRepository = new DrinkRepository();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CoffeeAdminResponse getCoffeeAdmin(HttpServletRequest request) {
        String username = authorizationService.getUsername(request);
        CoffeeAdminEntity adminEntity = coffeeAdminRepository.findByUsername(username);
        return CoffeeAdminResponse.builder()
                .id(adminEntity.getId())
                .username(adminEntity.getUsername())
                .build();
    }

    @SneakyThrows
    public void refreshDrink(HttpServletRequest request) {
        RefreshDrinkRequest body = objectMapper.readValue(
                request.getReader().lines().collect(Collectors.joining(System.lineSeparator())),
                RefreshDrinkRequest.class);
        DrinkEntity drinkEntity = drinkRepository.findById(body.getDrinkId());
        drinkRepository.updateCount(drinkEntity.getId(), drinkEntity.getPrice() + body.getCount());
    }
}
