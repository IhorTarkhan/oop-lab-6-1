package com.ihortarkhan.ooplab61.services;

import com.ihortarkhan.ooplab61.dto.request.RefreshDrinkRequest;
import com.ihortarkhan.ooplab61.dto.response.CoffeeAdminResponse;
import com.ihortarkhan.ooplab61.entity.CoffeeAdmin;
import com.ihortarkhan.ooplab61.entity.Drink;
import com.ihortarkhan.ooplab61.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoffeeAdminService {
    private final AuthorizationService authorizationService;
    private final DrinkRepository drinkRepository;

    public CoffeeAdminResponse getCoffeeAdmin() {
        CoffeeAdmin adminEntity = authorizationService.getCurrentCoffeeAdmin();
        return CoffeeAdminResponse.builder()
                .id(adminEntity.getId())
                .username(adminEntity.getUsername())
                .build();
    }

    @SneakyThrows
    public void refreshDrink(RefreshDrinkRequest request) {
        Drink drinkEntity = drinkRepository.getById(request.getDrinkId());
        drinkEntity.setCount(drinkEntity.getCount() + request.getCount());
        drinkRepository.save(drinkEntity);
    }
}
