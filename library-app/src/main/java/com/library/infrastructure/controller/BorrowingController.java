package com.library.infrastructure.controller;

import com.library.application.service.BorrowingService;
import com.library.domain.Borrowing;
import com.library.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/borrowings")
public class BorrowingController {
    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService){
        this.borrowingService = borrowingService;
    }
    @PostMapping("/borrow")
    public ResponseEntity<Borrowing> borrowBorrowing(@RequestParam("userId") UUID userId,@RequestParam("bookId") UUID bookId){
        Borrowing borrowing = borrowingService.borrowBook(userId, bookId);
        return ResponseEntity.ok(borrowing);
    }
    @PutMapping("/return/{borrowingId}")
    public ResponseEntity<Borrowing> returnBook(@PathVariable UUID borrowingId){
        Borrowing borrowing = borrowingService.returnBook(borrowingId);
        return ResponseEntity.ok(borrowing);
    }
}
