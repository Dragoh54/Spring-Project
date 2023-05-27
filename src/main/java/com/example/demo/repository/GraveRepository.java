package com.example.demo.repository;

import com.example.demo.entity.Grave;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GraveRepository extends JpaRepository<Grave, Long> {
    Optional<Grave> findById(Long id);
    Optional<Grave> findByName(String name);
    @Query("SELECT g FROM Graves g WHERE g.user.userId = :userId")
    List<Grave> findByUserId(Long userId);
}
