package com.ihortarkhan.ooplab61.services;

import com.ihortarkhan.ooplab61.dto.response.CoffeeUserResponse;
import com.ihortarkhan.ooplab61.entities.CoffeeUserEntity;
import com.ihortarkhan.ooplab61.repositories.CoffeeUserRepository;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletRequest;

public class CoffeeUserService {
    private final AuthorizationService authorizationService = new AuthorizationService();
    private final CoffeeUserRepository coffeeUserRepository = new CoffeeUserRepository();

    @SneakyThrows
    public CoffeeUserResponse getCoffeeUser(HttpServletRequest request) {
        String username = authorizationService.getUsername(request);
        CoffeeUserEntity coffeeUser = coffeeUserRepository.findByUsername(username);
        return CoffeeUserResponse.builder()
                .id(coffeeUser.getId())
                .username(coffeeUser.getUsername())
                .amount(coffeeUser.getAmount())
                .build();
    }
}
