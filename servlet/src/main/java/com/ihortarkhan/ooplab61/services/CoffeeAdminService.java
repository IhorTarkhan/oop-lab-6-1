package com.ihortarkhan.ooplab61.services;

import com.ihortarkhan.ooplab61.dto.response.CoffeeAdminResponse;
import com.ihortarkhan.ooplab61.entities.CoffeeAdminEntity;
import com.ihortarkhan.ooplab61.repositories.CoffeeAdminRepository;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletRequest;

public class CoffeeAdminService {
    private final AuthorizationService authorizationService = new AuthorizationService();
    private final CoffeeAdminRepository coffeeAdminRepository = new CoffeeAdminRepository();

    @SneakyThrows
    public CoffeeAdminResponse getCoffeeAdmin(HttpServletRequest request) {
        String username = authorizationService.getUsername(request);
        CoffeeAdminEntity adminEntity = coffeeAdminRepository.findByUsername(username);
        return CoffeeAdminResponse.builder()
                .id(adminEntity.getId())
                .username(adminEntity.getUsername())
                .build();
    }
}
