package com.library.application.service;

import com.library.domain.Author;
import com.library.dto.CreateAuthorRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AuthorService {
    Author addAuthor(CreateAuthorRequest request);
    Author getAuthorById(UUID id);
    Author updateAuthor(UUID id, CreateAuthorRequest request);
    void deleteAuthorById(UUID id);
    Page<Author> getAllAuthors(Pageable pageable);
}
