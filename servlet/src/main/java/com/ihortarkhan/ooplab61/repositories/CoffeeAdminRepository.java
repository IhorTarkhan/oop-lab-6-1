package com.ihortarkhan.ooplab61.repositories;

import com.ihortarkhan.ooplab61.entities.CoffeeAdminEntity;

public class CoffeeAdminRepository {
  public CoffeeAdminEntity findByUsername(String username) {
    return CoffeeAdminEntity.builder().id(-1L).username(username).build();
  }
}
