package com.library.domain;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "borrowings")
public class Borrowing extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "book_id") //FK sütun adını belirler
    @NotNull(message = "Book is required")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "User is required")
    private User user;
    private LocalDate borrowedAt;
    private LocalDate returnedAt;
    private Boolean isReturned; // Kitabın iade edilip edilmediğini tutar

    public Borrowing(){
    }
    public Borrowing(UUID id, Book book, User user, LocalDate borrowedAt, LocalDate returnedAt, Boolean isRetuned){
        super(id);
        this.book = book;
        this.user = user;
        this.borrowedAt = borrowedAt;
        this.returnedAt = returnedAt;
        this.isReturned = isRetuned;
    }
    public Book getBook() {
        return book;
    }
    public void setBook(Book book){
        this.book = book;
    }
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }
    public LocalDate getBorrowedAt() {
        return borrowedAt;
    }
    public void setBorrowedAt(LocalDate borrowedAt){
        this.borrowedAt = borrowedAt;
    }
    public LocalDate getReturnedAt() {
        return returnedAt;
    }
    public void setReturnedAt(LocalDate returnedAt){
        this.returnedAt = returnedAt;
    }
    public Boolean getIsReturned() {
        return isReturned;
    }
    public void setIsReturned(Boolean isReturned) {
        this.isReturned = isReturned;
    }
}
