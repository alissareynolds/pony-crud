package com.example.pony_project.controller;

import com.example.pony_project.exceptions.PonyNotFoundException;
import com.example.pony_project.model.Pony;
import com.example.pony_project.service.PonyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ponies")
public class PonyController {

    private final PonyService ponyService;

    public PonyController(PonyService ponyService) {
        this.ponyService = ponyService;
    }

    @PostMapping
    public ResponseEntity<Pony> createPony(@RequestBody Pony pony) {
        Pony newPony = ponyService.create(pony);
        return new ResponseEntity<>(newPony, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pony>> getAllPonies() {
        List<Pony> ponies = ponyService.getAll();
        return new ResponseEntity<>(ponies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pony> getPonyById(@PathVariable UUID id) {
        Pony newPony;
        try {
            newPony = ponyService.getById(id);
        } catch (PonyNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newPony, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Pony>> getPonyByName(@PathVariable String name) {
        List<Pony> ponies = ponyService.getByName(name);
        return new ResponseEntity<>(ponies, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pony> updatePony(@RequestBody Pony pony, @PathVariable UUID id) {
        Pony updatedPony;
        try {
           updatedPony = ponyService.update(pony, id);
        } catch (PonyNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedPony, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pony> patchPony(@RequestBody Pony pony, @PathVariable UUID id) {
        Pony updatedPony;
        try {
            updatedPony = ponyService.patch(pony, id);
        } catch (PonyNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedPony, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pony> deletePony(@PathVariable UUID id) {
        ponyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
