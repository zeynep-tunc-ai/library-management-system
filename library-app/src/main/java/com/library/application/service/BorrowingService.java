package com.library.application.service;

import com.library.domain.Borrowing;

import java.util.UUID;

public interface BorrowingService {
    Borrowing borrowBook(UUID userId, UUID bookId);
    Borrowing returnBook(UUID borrowingId);
}
