package com.library.api.controller;

import com.library.application.service.AuthorService;
import com.library.domain.Author;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController //REST denetleyicisi olduğunu belirtir
@RequestMapping("/com/library/api/authors")
@CrossOrigin(origins = "http://localhost:3000") //Frontend uygulamasının bu API'ye erişimine izin verir
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }
    @PostMapping
    public ResponseEntity<Author> addAuthor(@Valid @RequestBody Author author){
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
    public ResponseEntity<Author> updateAuthor(@PathVariable UUID id,@Valid @RequestBody Author author){
        Author updateAuthor = authorService.updateAuthor(id, author);
        return ResponseEntity.ok(updateAuthor);
    }
    @GetMapping("/paged")
    public ResponseEntity<Page<Author>> getAllAuthorsPaged(@ParameterObject @PageableDefault(page = 0, size = 10, sort = "name") Pageable pageable){
        Page<Author> authors = authorService.getAllAuthorsPaged(pageable);
        return ResponseEntity.ok(authors);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable UUID id){
        authorService.deleteAuthorById(id);
        return ResponseEntity.ok().build();
    }
}
