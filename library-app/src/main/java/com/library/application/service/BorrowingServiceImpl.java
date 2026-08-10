package com.library.application.service;

import com.library.application.repository.BookRepository;
import com.library.application.repository.BorrowingRepository;
import com.library.application.repository.UserRepository;
import com.library.domain.Book;
import com.library.domain.Borrowing;
import com.library.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional //Veritabanında fazla adımlı metodlar için kullanılır ve bir adımda hata olursa veritabanını ilk haline döndürür
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
        Book book = bookRepository.findById(bookId).orElse(null);//Kitaı ID'yle çağırır
        User user = userRepository.findById(userId).orElse(null);//Kullanıcıyı ID'yle bulur
        if (book == null) {
            throw new RuntimeException("Book not found!");
        }
        if (user == null) {
            throw new RuntimeException("User not found!");
        }
        if (book.getStockCount() <= 0) {
            throw new RuntimeException("Book has been borrowed.");
        }
        //Kullanıcının teslim etmediği kitap sayısını kontrol eder
        long activeBorrowingCount = borrowingRepository.countByUserIdAndIsReturnedFalse(userId);
        if (activeBorrowingCount >= 3){
            throw new RuntimeException("The user has reached the maximum book limit.");
        }
        //Kullanıcının bu kitabı önceden ödünç alıp almadığını kontrol eder
        List<Borrowing> activeBorrowings = borrowingRepository.findByUserIdAndBookId(userId, bookId);
        for (Borrowing borrowing : activeBorrowings) {
            if (borrowing.getBook().getId().equals(bookId)) {
                throw new RuntimeException("You have already borrowed this book!");
            }
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
        Borrowing borrowing = borrowingRepository.findById(borrowingId).orElse(null); //Ödünç kaydı var mı konrtol eder
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

    @Override
    public Page<Borrowing> getAllBorrowings(Pageable pageable) {
        return borrowingRepository.findAll(pageable);
    }

    @Override
    public Page<Borrowing> getAllActiveBorrowings(Pageable pageable) {
        return borrowingRepository.findByIsReturnedFalse(pageable);
    }

    @Override    public Page<Borrowing> getAllReturnedBorrowings(Pageable pageable) {
        return borrowingRepository.findByIsReturnedTrue(pageable);
    }
}
