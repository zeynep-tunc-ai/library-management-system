package com.library.application.service;

import com.library.domain.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {
    Book addBook(Book book);
    Book getBookById(UUID id);
    List<Book> getAllBooks();
    Book updateBook(UUID id, Book book);
    void deleteBookById(UUID id);
}
