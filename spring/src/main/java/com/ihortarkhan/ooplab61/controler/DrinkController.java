package com.ihortarkhan.ooplab61.controler;

import com.ihortarkhan.ooplab61.dto.response.DrinkResponse;
import com.ihortarkhan.ooplab61.services.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DrinkController {
    private final DrinkService drinkService;

    @GetMapping("/servlet_war_exploded/drinks")
    public List<DrinkResponse> getCurrentAdmin() {
        return drinkService.findAll();
    }
}
