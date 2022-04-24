package com.ihortarkhan.ooplab61.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DrinkEntity {
    private Long id;
    private String name;
    private Long price;
    private Long count;
}
