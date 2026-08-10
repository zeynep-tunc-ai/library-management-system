package com.library.application.repository;

import com.library.domain.Borrowing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, UUID>{
    //Verilen kullanıcı ID'sine ait henüz iade edilmemiş toplam ödünç kitap sayısını döner
    long countByUserIdAndIsReturnedFalse(UUID userId);
    //Verilen kitap ID'sine sahip ve henüz iade edilmemiş ödünç kaydı var mı diye kontrol eder
    boolean existsByBookIdAndIsReturnedFalse(UUID bookId);
    //Ödünç kitapları yani henüz iade edilememiş veritabanından parça parça çeker
    Page<Borrowing> findByIsReturnedFalse(Pageable pageable);
    //Teslim edilen kitapları veritabanından parça parça çeker
    Page<Borrowing> findByIsReturnedTrue(Pageable pageable);
    //Kullanıcıya ve kitaba ait tüm ödünç kayıtlarını getirir
    List<Borrowing> findByUserIdAndBookId(UUID userId, UUID bookId);
}
