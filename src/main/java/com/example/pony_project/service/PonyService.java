package com.example.pony_project.service;

import com.example.pony_project.exceptions.PonyNotFoundException;
import com.example.pony_project.model.Pony;
import com.example.pony_project.repository.PonyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PonyService {

    @Autowired
    PonyRepository ponyRepository;

    public Pony create(Pony pony) {
        Pony newPony = new Pony(pony.getName(), pony.getColor(), pony.getIsUnicorn(), pony.getIsPegasus(), pony.getAge());
        return ponyRepository.save(newPony);
    }

    public Pony getById(UUID id) {
        Optional<Pony> pony = ponyRepository.findById(id);
        if (pony.isEmpty()) {
            throw new PonyNotFoundException("A pony with that id is not found.");
        }
        return pony.get(); // .get() is how to get something out of an optional
    }

    public List<Pony> findAllPonies() {
        return ponyRepository.findAll();
    }

    public Pony getPonyByName(String name) {
        Optional<Pony> newPony = ponyRepository.findByName(name);
        if (newPony.isEmpty()) {
            throw new PonyNotFoundException("A pony with that name is not found.");
        }
        return newPony.get();
    }

    public Pony updatePony(Pony pony, UUID id) {
        Optional<Pony> oldPony = ponyRepository.findById(id);
        if (oldPony.isEmpty()) {
            throw new PonyNotFoundException("A pony with that id does not exist.");
        }
        Pony updatedPony = new Pony(id, pony.getName(), pony.getColor(), pony.getIsUnicorn(), pony.getIsPegasus(), pony.getAge());
        return ponyRepository.save(updatedPony);
    }

    public Pony deletePonyById(UUID id) {
        Optional<Pony> optionalPony = ponyRepository.findById(id);
        if (optionalPony.isEmpty()) {
            throw new PonyNotFoundException("A pony with that id was not found.");
        }
        ponyRepository.delete(optionalPony.get());
        return optionalPony.get();
    }

//    public Pony patchPony(Pony pony, UUID id) {
//    }
}
