package com.library.api.controller;

import com.library.application.service.AuthorService;
import com.library.domain.Author;
import com.library.dto.CreateAuthorRequest;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
//Dışarıdan gelen HTTP isteklerini karşılayıp servise yönlendiren katman
@RestController //REST denetleyicisi olduğunu belirtir
@RequestMapping("/com/library/api/authors")
@CrossOrigin(origins = "**") //Frontend uygulamasının bu API'ye erişimine izin verir
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }
    @PostMapping
    public ResponseEntity<Author> addAuthor(@Valid @RequestBody CreateAuthorRequest request){
        Author savedAuthor = authorService.addAuthor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthor);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable UUID id){
        Author author = authorService.getAuthorById(id);
        return ResponseEntity.ok(author);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable UUID id,@Valid @RequestBody CreateAuthorRequest request){
        Author updateAuthor = authorService.updateAuthor(id, request);
        return ResponseEntity.ok(updateAuthor);
    }
    @GetMapping
    public ResponseEntity<Page<Author>> getAllAuthors(@ParameterObject @PageableDefault(page = 0, size = 10, sort = "fullName") Pageable pageable){
        Page<Author> authors = authorService.getAllAuthors(pageable);
        return ResponseEntity.ok(authors);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable UUID id){
        authorService.deleteAuthorById(id);
        return ResponseEntity.ok().build();
    }
}
