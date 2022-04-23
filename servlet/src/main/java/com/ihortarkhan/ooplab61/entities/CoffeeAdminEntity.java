package com.ihortarkhan.ooplab61.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoffeeAdminEntity {
    private Long id;
    private String username;
}
