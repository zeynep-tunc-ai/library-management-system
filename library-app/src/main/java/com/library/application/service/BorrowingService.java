package com.library.application.service;

import com.library.domain.Borrowing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BorrowingService {
    Borrowing borrowBook(UUID userId, UUID bookId);
    Borrowing returnBook(UUID borrowingId);
    //Tüm ödünç kayıtlarını sayfalı getirir
    Page<Borrowing> getAllBorrowings(Pageable pageable);
    //Aktif ödünç kayıtlarını getirir
    Page<Borrowing> getAllActiveBorrowings(Pageable pageable);
    //İade edilmiş kitapları getirir
    Page<Borrowing> getAllReturnedBorrowings(Pageable pageable);
}
