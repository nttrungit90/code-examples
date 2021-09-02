package com.spring.example.statemachine.repository;

import java.util.Optional;

import com.spring.example.statemachine.domain.Runsheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunsheetRepository extends JpaRepository<Runsheet, Long> {

    Optional<Runsheet> findByCourierId(Long courierId);
}
