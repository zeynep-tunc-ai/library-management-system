package com.library.application.service;

import com.library.application.repository.BookRepository;
import com.library.application.repository.BorrowingRepository;
import com.library.application.repository.UserRepository;
import com.library.domain.Book;
import com.library.domain.Borrowing;
import com.library.domain.User;
import java.time.LocalDate;

import java.util.UUID;

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
        Book book = bookRepository.findById(bookId);
        User user = userRepository.findById(userId);
        if (book == null) {
            System.out.println("Book not found!");
            return null;
        }
        if (user == null) {
            System.out.println("User not found!");
            return null;
        }
        if (book.getStockCount() <= 0) {
            System.out.println("Book has been borrowed.");
            return null;
        }
        long activeBorrowingCount = borrowingRepository.countByUserIdAndIsReturnedFalse(userId);
        if (activeBorrowingCount >= 3){
            System.out.println("The user has reached the maximum book limit.");
            return null;
        }
        book.setStockCount(book.getStockCount() - 1);
        bookRepository.save(book);

        Borrowing borrowing = new Borrowing();
        borrowing.setBook(book);
        borrowing.setUser(user);
        borrowing.setBorrowedAt(LocalDate.now());
        borrowing.setIsReturned(false);

        return borrowingRepository.save(borrowing);
    }

    @Override
    public Borrowing returnBook(UUID borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId);
        if (borrowing == null)
        {
            System.out.println("Borrowing not found!");
            return null;
        }
        borrowing.setReturnedAt(LocalDate.now());
        borrowing.setIsReturned(true);
        Book book = borrowing.getBook();
        book.setStockCount(book.getStockCount() +1);
        bookRepository.save(book);

        return borrowingRepository.save(borrowing);
    }
}
