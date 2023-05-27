package com.example.demo.repository;

import com.example.demo.entity.Grave;
import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GraveRepository extends JpaRepository<Grave, Long> {
    Optional<Grave> findById(Long id);
    Optional<Grave> findByPersonName(String name);
}
