package com.library.application.repository;

import com.library.domain.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository {
    void save(Author author);
    void delete(UUID id);
    Author findById(UUID id);
    List<Author> findAll();
    Author findByName(String name);
}
