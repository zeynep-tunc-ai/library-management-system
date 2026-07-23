package com.library.application.service;

import com.library.application.repository.BookRepository;
import com.library.application.repository.BorrowingRepository;
import com.library.application.repository.UserRepository;
import com.library.domain.Book;
import com.library.domain.Borrowing;
import com.library.domain.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class BorrowingServiceImpl implements BorrowingService{
    private final BorrowingRepository borrowingRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BorrowingServiceImpl(BorrowingRepository borrowingRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.borrowingRepository = borrowingRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Borrowing borrowBook(UUID userId, UUID bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (book == null) {
            throw new RuntimeException("Book not found!");
        }
        if (user == null) {
            throw new RuntimeException("User not found!");
        }
        if (book.getStockCount() <= 0) {
            throw new RuntimeException("Book has been borrowed.");
        }
        long activeBorrowingCount = borrowingRepository.countByUserIdAndIsReturnedFalse(userId);
        if (activeBorrowingCount >= 3){
            throw new RuntimeException("The user has reached the maximum book limit.");
        }
        book.setStockCount(book.getStockCount() - 1);
        bookRepository.save(book);

        Borrowing borrowing = new Borrowing();
        borrowing.setId(UUID.randomUUID());
        borrowing.setBook(book);
        borrowing.setUser(user);
        borrowing.setBorrowedAt(LocalDate.now());
        borrowing.setIsReturned(false);

        return borrowingRepository.save(borrowing);
    }

    @Override
    public Borrowing returnBook(UUID borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId).orElse(null);
        if (borrowing == null)
        {
            throw new RuntimeException("Borrowing not found!");
        }
        if (borrowing.getIsReturned()) {
            throw new RuntimeException("This book has already been returned!");
        }
        borrowing.setReturnedAt(LocalDate.now());
        borrowing.setIsReturned(true);
        Book book = borrowing.getBook();
        book.setStockCount(book.getStockCount() +1);
        bookRepository.save(book);

        return borrowingRepository.save(borrowing);
    }
}
