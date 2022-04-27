package com.ihortarkhan.ooplab61.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoffeeUserResponse {
    private Long id;
    private String username;
    private Long amount;
}
