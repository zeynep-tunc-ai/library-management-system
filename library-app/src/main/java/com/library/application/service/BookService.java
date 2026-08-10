package com.library.application.service;

import com.library.domain.Book;
import com.library.dto.CreateBookRequest;
import com.library.dto.UpdateBookRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookService {
    Book addBook(CreateBookRequest request);
    Book getBookById(UUID id);
    Book updateBook(UUID id, UpdateBookRequest request);
    void deleteBookById(UUID id);
    Page<Book> getAllBooks(Pageable pageable); //Sayfalama yani kitapları veritabanından parça parça çeker
}
