package com.edwinbaquiax.library.controller;


import com.edwinbaquiax.library.models.entities.Client;
import com.edwinbaquiax.library.models.entities.Rent;
import com.edwinbaquiax.library.repositories.ClientRepository;
import com.edwinbaquiax.library.services.IClientManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/clients")
public class ClientController {
    @Autowired
    ClientRepository repo;

    @GetMapping
    public ResponseEntity<?> getClients(){
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    public ResponseEntity<?> post(
            @RequestBody Client item
    ){
        return ResponseEntity.ok(repo.save(item));
    }
    @DeleteMapping
    public ResponseEntity<?> delete(
            @RequestBody Client item
    ){
        repo.delete(item);
        return (ResponseEntity<?>) ResponseEntity.ok();
    }
    @PutMapping
    public ResponseEntity<?> put(
            @RequestBody Client item
    ){
        return ResponseEntity.ok(repo.save(item));
    }
}
