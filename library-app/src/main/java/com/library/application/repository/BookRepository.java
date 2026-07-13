package com.library.application.repository;

import com.library.domain.Book;

import java.util.List;
import java.util.UUID;

public interface BookRepository {
    void save(Book book);
    void delete(UUID id);
    Book findById(UUID id);
    List<Book> findAll();
    Book findByName(String name);
}
