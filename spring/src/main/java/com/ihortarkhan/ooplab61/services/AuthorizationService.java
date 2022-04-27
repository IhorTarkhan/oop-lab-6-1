package com.ihortarkhan.ooplab61.services;

import com.ihortarkhan.ooplab61.entity.CoffeeAdmin;
import com.ihortarkhan.ooplab61.entity.CoffeeUser;
import lombok.SneakyThrows;
import org.keycloak.TokenVerifier;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Service
public class AuthorizationService {
    public CoffeeUser getCurrentCoffeeUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (CoffeeUser) principal;
    }

    public CoffeeAdmin getCurrentCoffeeAdmin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (CoffeeAdmin) principal;
    }
}
