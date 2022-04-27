package com.ihortarkhan.ooplab61.repository;

import com.ihortarkhan.ooplab61.entity.Drink;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepositoryImplementation<Drink, Long> {
    List<Drink> findByOrderById();
}
