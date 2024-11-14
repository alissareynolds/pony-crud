package com.example.pony_project.service;

import com.example.pony_project.exceptions.PonyNotFoundException;
import com.example.pony_project.model.Pony;
import com.example.pony_project.repository.PonyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PonyService {

    private final PonyRepository ponyRepository;

    public PonyService(PonyRepository ponyRepository) {
        this.ponyRepository = ponyRepository;
    }

    public Pony create(Pony pony) {
       return ponyRepository.save(pony);
    }

    public List<Pony> getAll() {
        return ponyRepository.findAll();
    }

    public Pony getById(UUID id) {
        Optional<Pony> optionalPony = ponyRepository.findById(id);
        if (optionalPony.isEmpty()) {
            throw new PonyNotFoundException("A pony with id: " + id + " was not found.");
        }
        return optionalPony.get();
    }

    public List<Pony> getByName(String name) {
        return ponyRepository.findByName(name);
    }

    public Pony update(Pony pony, UUID id) {
        Optional<Pony> optionalPony = ponyRepository.findById(id);
        if (optionalPony.isEmpty()) {
            throw new PonyNotFoundException("A pony with id: " + id + " was not found.");
        }
        pony.setId(id);
        return ponyRepository.save(pony);
    }

    public Pony patch(Pony pony, UUID id) {
        Optional<Pony> optionalPony = ponyRepository.findById(id);
        if (optionalPony.isEmpty()) {
            throw new PonyNotFoundException("A pony with id: " + id + " was not found.");
        }
        Pony updatedPony = optionalPony.get();
        if (pony.getName() != null) {
            updatedPony.setName(pony.getName());
        }
        if (pony.getColor() != null) {
            updatedPony.setColor(pony.getColor());
        }
        if (pony.getIsUnicorn() != null) {
            updatedPony.setIsUnicorn(pony.getIsUnicorn());
        }
        if (pony.getIsPegasus() != null) {
            updatedPony.setIsPegasus(pony.getIsPegasus());
        }
        if (pony.getAge() != null) {
            updatedPony.setAge(pony.getAge());
        }
        return ponyRepository.save(updatedPony);
    }

    public void delete(UUID id) {
        ponyRepository.deleteById(id);
    }


}
