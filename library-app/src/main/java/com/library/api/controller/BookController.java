package com.library.api.controller;

import com.library.application.service.BookService;
import com.library.domain.Book;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/com/library/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book){
        Book savedBook = bookService.addBook(book);
        return ResponseEntity.ok(savedBook);
    }
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable UUID id){
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }
    @GetMapping("/paged")
    public ResponseEntity<Page<Book>> getAllBooksPaged(@ParameterObject @PageableDefault(page = 0, size = 10, sort = "title")Pageable pageable){
        Page<Book> books = bookService.getAllBooksPaged(pageable);
        return ResponseEntity.ok(books);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable UUID id, @Valid @RequestBody Book book){
        Book updateBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updateBook);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookBYId(@PathVariable UUID id){
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }
}
