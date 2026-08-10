package com.library.api.controller;

import com.library.application.service.BorrowingService;
import com.library.domain.Borrowing;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/com/library/api/borrowings")
@CrossOrigin(origins = "**")
public class BorrowingController {
    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService){
        this.borrowingService = borrowingService;
    }
    //Yeni ödünç kaydı oluşturur
    @PostMapping
    public ResponseEntity<Borrowing> borrowBorrowing(@RequestParam("userId") UUID userId, @RequestParam("bookId") UUID bookId){
        Borrowing borrowing = borrowingService.borrowBook(userId, bookId);
        return ResponseEntity.ok(borrowing);
    }
    //Mevcut ödünç kaydını iade olarak günceller
    @PutMapping("/{borrowingId}/return")
    public ResponseEntity<Borrowing> returnBook(@PathVariable UUID borrowingId){
        Borrowing borrowing = borrowingService.returnBook(borrowingId);
        return ResponseEntity.ok(borrowing);
    }
    //Tüm ödünçleri getirir
    @GetMapping
    public ResponseEntity<Page<Borrowing>> getAllBorrowings(@ParameterObject @PageableDefault(page = 0, size = 10, sort = "borrowedAt") Pageable pageable){
        Page<Borrowing> borrowings = borrowingService.getAllBorrowings(pageable);
        return ResponseEntity.ok(borrowings);
    }
    //Aktif ödünçleri getirir
    @GetMapping("/active")
    public ResponseEntity<Page<Borrowing>> getAllActiveBorrowings(@ParameterObject @PageableDefault(page = 0, size = 10, sort = "borrowedAt") Pageable pageable){
        Page<Borrowing> borrowings = borrowingService.getAllActiveBorrowings(pageable);
        return ResponseEntity.ok(borrowings);
    }
    @GetMapping("/returned")
    //İade edilen ödünçleri getirir
    public ResponseEntity<Page<Borrowing>> getAllReturnedBorrowings(@ParameterObject @PageableDefault(page = 0, size = 10, sort = "returnedAt") Pageable pageable){
        Page<Borrowing> borrowings = borrowingService.getAllReturnedBorrowings(pageable);
        return ResponseEntity.ok(borrowings);
    }
}
