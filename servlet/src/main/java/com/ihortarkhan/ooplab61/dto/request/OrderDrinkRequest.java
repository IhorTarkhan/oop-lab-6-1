package com.ihortarkhan.ooplab61.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDrinkRequest {
    private Long drinkId;
    private Long count;
}
