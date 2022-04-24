package com.ihortarkhan.ooplab61.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDrinkRequest {
    private Long drinkId;
    private Long count;
}
