package com.library.application.service;

import com.library.application.repository.AuthorRepository;
import com.library.application.repository.BookRepository;
import com.library.domain.Author;
import com.library.domain.Book;
import com.library.dto.CreateBookRequest;
import com.library.dto.UpdateBookRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    @Override
    public Book addBook(CreateBookRequest request) {
        //DTO'dan gelen yazar ID'si ile yazarı veritabanında arar
        Author author = authorRepository.findById(request.getAuthorId()).orElseThrow(() -> new RuntimeException("Author not found with id: " + request.getAuthorId()));

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setStockCount(request.getStockCount());
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(UUID id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not founded"));
    }

    @Override
    public Book updateBook(UUID id, UpdateBookRequest request) {
        Book foundBook = getBookById(id);

        if (request.getStockCount() != null) {
            foundBook.setStockCount(request.getStockCount());
        }

        return bookRepository.save(foundBook);
    }

    @Override
    public void deleteBookById(UUID id) {
        Book bookToDelete = getBookById(id);
        bookRepository.deleteById(bookToDelete.getId());

        }

    @Override
    public Page<Book> getAllBooks(Pageable pageable) { //Kitapları istenilen sayfaya göre getirir
        return bookRepository.findAll(pageable);
    }
}

