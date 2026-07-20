package com.library.infrastructure.controller;

import com.library.application.service.BookService;
import com.library.domain.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping
    public ResponseEntity<Book> addAuthor(@RequestBody Book book){
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
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable UUID id, @RequestBody Book book){
        Book updateBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updateBook);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookBYId(@PathVariable UUID id){
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }
}
