package com.ihortarkhan.ooplab61.repositories;

import com.ihortarkhan.ooplab61.entities.CoffeeUserEntity;

public class CoffeeUserRepository {
  public CoffeeUserEntity findByUsername(String username) {
    return CoffeeUserEntity.builder().id(-2L).username(username).build();
  }
}
