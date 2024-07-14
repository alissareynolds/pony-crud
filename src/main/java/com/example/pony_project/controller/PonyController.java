package com.example.pony_project.controller;

import com.example.pony_project.exceptions.PonyNotFoundException;
import com.example.pony_project.model.Pony;
import com.example.pony_project.repository.PonyRepository;
import com.example.pony_project.service.PonyService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ponies")
public class PonyController {

    @Autowired
    PonyService ponyService;

    @PostMapping
    public ResponseEntity<Pony> createPony(@RequestBody Pony pony) {
        Pony newPony = ponyService.create(pony);
        return new ResponseEntity<>(newPony, HttpStatus.CREATED);
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

    @GetMapping
    public ResponseEntity<List<Pony>> getAllPonies() {
        List<Pony> ponies = ponyService.findAllPonies();
        return new ResponseEntity<>(ponies, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Pony> getPonyByName(@PathVariable String name) {
        Pony newPony;
        try {
            newPony = ponyService.getPonyByName(name);
        } catch (PonyNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newPony, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pony> updatePony(@RequestBody Pony pony, @PathVariable UUID id) {
        Pony updatedPony;
        try {
           updatedPony = ponyService.updatePony(pony, id);
        } catch (PonyNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedPony, HttpStatus.OK);
    }
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<Pony> patchPony(@PathVariable UUID id) {
//          //if field not null that update/  check if old record exists if doesnt throw 404
//     //check updated record to see it ifs null
//        Pony updatedPony;
//        try {
//            updatedPony = ponyService.patchPony(id);
//        } catch (PonyNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Pony> deletePonyById(@PathVariable UUID id) {
        Pony pony;
        try {
            pony = ponyService.deletePonyById(id);
        } catch (PonyNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pony, HttpStatus.OK);
    }


}
