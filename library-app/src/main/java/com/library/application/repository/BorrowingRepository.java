package com.library.application.repository;

import com.library.domain.Borrowing;

import java.util.List;
import java.util.UUID;

public interface BorrowingRepository {
    void save(Borrowing borrowing);
    Borrowing findById(UUID id);
    List<Borrowing> finAll();
    long countByUserIdAndIsReturnedFalse(UUID userId);
    boolean existsByBookIdAndIsReturnedFalse(UUID bookId);
}
