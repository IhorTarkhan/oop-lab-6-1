package com.ihortarkhan.ooplab61.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DrinkResponse {
    private Long id;
    private String name;
    private Long price;
    private Long count;
}
