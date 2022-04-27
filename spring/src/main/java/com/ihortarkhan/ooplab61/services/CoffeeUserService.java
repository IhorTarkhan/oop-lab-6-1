package com.ihortarkhan.ooplab61.services;

import com.ihortarkhan.ooplab61.dto.request.OrderDrinkRequest;
import com.ihortarkhan.ooplab61.dto.response.CoffeeUserResponse;
import com.ihortarkhan.ooplab61.entity.CoffeeUser;
import com.ihortarkhan.ooplab61.entity.Drink;
import com.ihortarkhan.ooplab61.repository.CoffeeUserRepository;
import com.ihortarkhan.ooplab61.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CoffeeUserService {
    private final AuthorizationService authorizationService;
    private final CoffeeUserRepository coffeeUserRepository;
    private final DrinkRepository drinkRepository;

    public CoffeeUserResponse getCoffeeUser() {
        CoffeeUser coffeeUser = authorizationService.getCurrentCoffeeUser();
        return CoffeeUserResponse.builder()
                .id(coffeeUser.getId())
                .username(coffeeUser.getUsername())
                .amount(coffeeUser.getAmount())
                .build();
    }

    @SneakyThrows
    public void orderDrink(OrderDrinkRequest request) {
        CoffeeUser coffeeUser = authorizationService.getCurrentCoffeeUser();
        Drink drinkEntity = drinkRepository.getById(request.getDrinkId());
        drinkEntity.setCount(drinkEntity.getCount() - request.getCount());
        coffeeUser.setAmount(coffeeUser.getAmount() - drinkEntity.getPrice() * request.getCount());
        drinkRepository.save(drinkEntity);
        coffeeUserRepository.save(coffeeUser);
    }
}
