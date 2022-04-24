package com.ihortarkhan.ooplab61.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoffeeAdminResponse {
    private Long id;
    private String username;
}
