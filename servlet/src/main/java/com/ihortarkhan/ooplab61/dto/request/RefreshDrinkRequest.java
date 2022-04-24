package com.ihortarkhan.ooplab61.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshDrinkRequest {
    private Long drinkId;
    private Long count;
}
