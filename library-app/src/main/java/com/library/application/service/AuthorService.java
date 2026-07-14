package com.library.application.service;

import com.library.domain.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
    Author addAuthor(Author author);
    Author getAuthorById(UUID id);
    List<Author> getAllAuthors();
    Author updateAuthor(UUID id, Author author);
    void deleteAuthorById(UUID id);
}
