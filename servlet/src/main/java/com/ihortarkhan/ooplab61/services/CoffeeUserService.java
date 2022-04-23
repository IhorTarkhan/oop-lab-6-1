package com.ihortarkhan.ooplab61.services;

import com.ihortarkhan.ooplab61.entities.CoffeeUserEntity;
import com.ihortarkhan.ooplab61.repositories.CoffeeUserRepository;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletRequest;

public class CoffeeUserService {
  private final AuthorizationService authorizationService = new AuthorizationService();
  private final CoffeeUserRepository coffeeUserRepository = new CoffeeUserRepository();

  @SneakyThrows
  public CoffeeUserEntity getCoffeeUser(HttpServletRequest request) {
    String username = authorizationService.getUsername(request);
    return coffeeUserRepository.findByUsername(username);
  }
}
