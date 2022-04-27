package com.ihortarkhan.ooplab61.controler;

import com.ihortarkhan.ooplab61.dto.request.RefreshDrinkRequest;
import com.ihortarkhan.ooplab61.dto.response.CoffeeAdminResponse;
import com.ihortarkhan.ooplab61.services.CoffeeAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final CoffeeAdminService coffeeAdminService;

    @GetMapping("/servlet_war_exploded/current-admin")
    public CoffeeAdminResponse getCurrentAdmin() {
        return coffeeAdminService.getCoffeeAdmin();
    }

    @PutMapping("/servlet_war_exploded/refresh-drink")
    public void refreshDrink(@RequestBody RefreshDrinkRequest request) {
        coffeeAdminService.refreshDrink(request);
    }
}
