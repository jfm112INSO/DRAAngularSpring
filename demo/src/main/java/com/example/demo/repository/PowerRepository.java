package com.example.demo.repository;

import com.example.demo.entity.Power;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PowerRepository extends JpaRepository<Power, Long> {
    Optional<Power> findByName(String name);
}
