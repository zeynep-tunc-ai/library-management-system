package com.library.api.controller;

import com.library.application.service.BookService;
import com.library.domain.Book;
import com.library.dto.CreateBookRequest;
import com.library.dto.UpdateBookRequest;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/com/library/api/books")
@CrossOrigin(origins = "**")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody CreateBookRequest request){
        Book savedBook = bookService.addBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable UUID id){
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }
    //Sayfalanmış kitap listesini getirir
    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(@ParameterObject @PageableDefault(page = 0, size = 10, sort = "title")Pageable pageable){
        Page<Book> books = bookService.getAllBooks(pageable);
        return ResponseEntity.ok(books);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable UUID id, @Valid @RequestBody UpdateBookRequest request){
        Book updateBook = bookService.updateBook(id, request);
        return ResponseEntity.ok(updateBook);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable UUID id){
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }
}
