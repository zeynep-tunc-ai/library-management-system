package com.library.application.repository;

import com.library.domain.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, UUID>{
    long countByUserIdAndIsReturnedFalse(UUID userId);
    boolean existsByBookIdAndIsReturnedFalse(UUID bookId);
}
