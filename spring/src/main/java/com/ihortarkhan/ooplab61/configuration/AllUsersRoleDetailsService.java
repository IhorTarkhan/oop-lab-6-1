package com.ihortarkhan.ooplab61.configuration;

import com.ihortarkhan.ooplab61.repository.CoffeeAdminRepository;
import com.ihortarkhan.ooplab61.repository.CoffeeUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AllUsersRoleDetailsService implements UserDetailsService {
    private final CoffeeAdminRepository coffeeAdminRepository;
    private final CoffeeUserRepository coffeeUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<? extends UserDetails> client = coffeeAdminRepository.findByUsername(username);
        if (client.isPresent()) {
            return client.get();
        }
        Optional<? extends UserDetails> superAdmin = coffeeUserRepository.findByUsername(username);
        if (superAdmin.isPresent()) {
            return superAdmin.get();
        }
        throw new RuntimeException();
    }
}
