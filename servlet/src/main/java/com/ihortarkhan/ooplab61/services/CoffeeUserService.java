package com.ihortarkhan.ooplab61.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihortarkhan.ooplab61.dto.request.OrderDrinkRequest;
import com.ihortarkhan.ooplab61.dto.response.CoffeeUserResponse;
import com.ihortarkhan.ooplab61.entities.CoffeeUserEntity;
import com.ihortarkhan.ooplab61.entities.DrinkEntity;
import com.ihortarkhan.ooplab61.repositories.CoffeeUserRepository;
import com.ihortarkhan.ooplab61.repositories.DrinkRepository;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

public class CoffeeUserService {
    private final AuthorizationService authorizationService = new AuthorizationService();
    private final CoffeeUserRepository coffeeUserRepository = new CoffeeUserRepository();
    private final DrinkRepository drinkRepository = new DrinkRepository();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CoffeeUserResponse getCoffeeUser(HttpServletRequest request) {
        String username = authorizationService.getUsername(request);
        CoffeeUserEntity coffeeUser = coffeeUserRepository.findByUsername(username);
        return CoffeeUserResponse.builder()
                .id(coffeeUser.getId())
                .username(coffeeUser.getUsername())
                .amount(coffeeUser.getAmount())
                .build();
    }

    @SneakyThrows
    public void orderDrink(HttpServletRequest request) {
        OrderDrinkRequest body = objectMapper.readValue(
                request.getReader().lines().collect(Collectors.joining(System.lineSeparator())),
                OrderDrinkRequest.class);
        String username = authorizationService.getUsername(request);
        CoffeeUserEntity coffeeUser = coffeeUserRepository.findByUsername(username);
        DrinkEntity drinkEntity = drinkRepository.findById(body.getDrinkId());
        drinkRepository.updateCount(drinkEntity.getId(), drinkEntity.getCount() - body.getCount());
        coffeeUserRepository.updateAmount(coffeeUser.getId(), coffeeUser.getAmount() - drinkEntity.getPrice() * body.getCount());
    }
}
