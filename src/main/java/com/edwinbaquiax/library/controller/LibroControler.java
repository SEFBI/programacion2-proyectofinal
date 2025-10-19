package com.edwinbaquiax.library.controller;

import com.edwinbaquiax.library.models.entities.Book;
import com.edwinbaquiax.library.models.entities.Client;
import com.edwinbaquiax.library.repositories.BookRepository;
import com.edwinbaquiax.library.repositories.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/libros")
public class LibroControler {
    @Autowired
    BookRepository repo;

    @GetMapping
    public ResponseEntity<?> get(){
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    public ResponseEntity<?> post(
            @RequestBody Book item
    ){
        return ResponseEntity.ok(repo.save(item));
    }
    @DeleteMapping
    public ResponseEntity<?> delete(
            @RequestBody Book item
    ){
        repo.delete(item);
        return (ResponseEntity<?>) ResponseEntity.ok();
    }
    @PutMapping
    public ResponseEntity<?> put(
            @RequestBody Book item
    ){
        return ResponseEntity.ok(repo.save(item));
    }
}