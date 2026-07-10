package com.library.domain;

import java.time.LocalDate;

public class Borrowing {
    private Long id;
    private Book book;
    private User user;
    private LocalDate borrowedAt;
    private LocalDate returnedAt;
    private Boolean isReturned;

    public Borrowing(){
    }
    public Borrowing(Long id, Book book, User user, LocalDate borrowedAt, LocalDate returnedAt, Boolean isRetuned){
        this.id = id;
        this.book = book;
        this.user = user;
        this.borrowedAt = borrowedAt;
        this.returnedAt = returnedAt;
        this.isReturned = isRetuned;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
