package com.edwinbaquiax.library.controller;


import com.edwinbaquiax.library.models.entities.Rent;
import com.edwinbaquiax.library.repositories.ClientRepository;
import com.edwinbaquiax.library.repositories.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/alquileres")
public class AlquierController {
    @Autowired
    RentRepository repo;

    @GetMapping
    public ResponseEntity<?> get(){
        return ResponseEntity.ok(repo.findAll());
    }
    @PostMapping
    public ResponseEntity<?> post(
            @RequestBody Rent renta
            ){
        return ResponseEntity.ok(repo.save(renta));
    }
    @DeleteMapping
    public ResponseEntity<?> delete(
            @RequestBody Rent renta
    ){
        repo.delete(renta);
        return (ResponseEntity<?>) ResponseEntity.ok();
    }
    @PutMapping
    public ResponseEntity<?> put(
            @RequestBody Rent renta
    ){
        return ResponseEntity.ok(repo.save(renta));
    }
}
