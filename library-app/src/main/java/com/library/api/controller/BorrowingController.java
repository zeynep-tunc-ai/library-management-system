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
@CrossOrigin(origins = "http://localhost:3000")
public class BorrowingController {
    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService){
        this.borrowingService = borrowingService;
    }
    //Yeni ödünç kaydı oluşturur
    @PostMapping("/borrow")
    public ResponseEntity<Borrowing> borrowBorrowing(@RequestParam("userId") UUID userId, @RequestParam("bookId") UUID bookId){
        Borrowing borrowing = borrowingService.borrowBook(userId, bookId);
        return ResponseEntity.ok(borrowing);
    }
    //Mevcut ödünç kaydını iade olarak günceller
    @PutMapping("/return/{borrowingId}")
    public ResponseEntity<Borrowing> returnBook(@PathVariable UUID borrowingId){
        Borrowing borrowing = borrowingService.returnBook(borrowingId);
        return ResponseEntity.ok(borrowing);
    }
    @GetMapping("/paged")
    public ResponseEntity<Page<Borrowing>> getAllBorrowingsPaged(@ParameterObject @PageableDefault(page = 0, size = 10, sort = "borrowDate") Pageable pageable){
        Page<Borrowing> borrowings = borrowingService.getAllBorrowings(pageable);
        return ResponseEntity.ok(borrowings);
    }
    @GetMapping("/active/paged")
    public ResponseEntity<Page<Borrowing>> getAllActiveBorrowingsPaged(@ParameterObject @PageableDefault(page = 0, size = 10, sort = "borrowDate") Pageable pageable){
        Page<Borrowing> borrowings = borrowingService.getAllActiveBorrowings(pageable);
        return ResponseEntity.ok(borrowings);
    }
    @GetMapping("/returned/paged")
    public ResponseEntity<Page<Borrowing>> getAllReturnedBorrowingsPaged(@ParameterObject @PageableDefault(page = 0, size = 10, sort = "returnDate") Pageable pageable){
        Page<Borrowing> borrowings = borrowingService.getAllReturnedBorrowings(pageable);
        return ResponseEntity.ok(borrowings);
    }
}
