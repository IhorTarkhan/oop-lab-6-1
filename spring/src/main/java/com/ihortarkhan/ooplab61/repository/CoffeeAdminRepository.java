package com.ihortarkhan.ooplab61.repository;

import com.ihortarkhan.ooplab61.entity.CoffeeAdmin;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoffeeAdminRepository extends JpaRepositoryImplementation<CoffeeAdmin, Long> {
    Optional<CoffeeAdmin> findByUsername(String username);
}
