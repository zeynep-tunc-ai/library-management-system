package com.library.infrastructure.controller;

import com.library.application.service.AuthorService;
import com.library.domain.Author;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }
    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody Author author){
        Author savedAuthor = authorService.addAuthor(author);
        return ResponseEntity.ok(savedAuthor);
    }
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors()
    {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable UUID id){
        Author author = authorService.getAuthorById(id);
        return ResponseEntity.ok(author);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable UUID id, @RequestBody Author author){
        Author updateAuthor = authorService.updateAuthor(id, author);
        return ResponseEntity.ok(updateAuthor);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable UUID id){
        authorService.deleteAuthorById(id);
        return ResponseEntity.ok().build();
    }
}
