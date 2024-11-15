package com.example.pony_project.repository;

import com.example.pony_project.model.Pony;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PonyRepository extends JpaRepository<Pony, UUID> {
    List<Pony> findByName(String name);
}
