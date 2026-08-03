package com.library.application.service;

import com.library.application.repository.BookRepository;
import com.library.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(UUID id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not founded"));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(UUID id, Book book) {
        Book foundBook = getBookById(id);
        foundBook.setTitle(book.getTitle());
        return bookRepository.save(foundBook);
    }

    @Override
    public void deleteBookById(UUID id) {
        Book bookToDelete = getBookById(id);
        bookRepository.deleteById(bookToDelete.getId());

        }

    @Override
    public Page<Book> getAllBooksPaged(Pageable pageable) { //Kitapları istenilen sayfaya göre getirir
        return bookRepository.findAll(pageable);
    }
}

