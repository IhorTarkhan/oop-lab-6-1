package com.ihortarkhan.ooplab61.repository;

import com.ihortarkhan.ooplab61.entity.CoffeeUser;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoffeeUserRepository extends JpaRepositoryImplementation<CoffeeUser, Long> {
    Optional<CoffeeUser> findByUsername(String username);
}
