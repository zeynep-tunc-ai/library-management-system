package com.library.application.service;

import com.library.application.repository.AuthorRepository;
import com.library.domain.Author;

import java.util.List;
import java.util.UUID;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author getAuthorById(UUID id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author updateAuthor(UUID id, Author author) {
        Author foundAuthor = getAuthorById(id);
        foundAuthor.setFullName(author.getFullName());
        return authorRepository.save(foundAuthor);
    }

    @Override
    public void deleteAuthorById(UUID id) {
        Author authorToDelete = getAuthorById(id);
        authorRepository.delete(authorToDelete.getId());
    }
}
