package com.ihortarkhan.ooplab61.controler;

import com.ihortarkhan.ooplab61.dto.request.OrderDrinkRequest;
import com.ihortarkhan.ooplab61.dto.response.CoffeeUserResponse;
import com.ihortarkhan.ooplab61.services.CoffeeUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final CoffeeUserService coffeeUserService;

    @GetMapping("/servlet_war_exploded/current-user")
    public CoffeeUserResponse getCurrentUser() {
        return coffeeUserService.getCoffeeUser();
    }

    @PostMapping("/servlet_war_exploded/order-drink")
    public void orderDrink(@RequestBody OrderDrinkRequest request) {
        coffeeUserService.orderDrink(request);
    }
}
