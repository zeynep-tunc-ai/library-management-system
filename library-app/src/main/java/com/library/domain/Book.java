package com.library.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
@Table(name = "books")
public class Book extends BaseEntity{
    @NotBlank(message = "The book title cannot be empty")
    private String title;
    @Min(value = 0, message = "Stock quantity cannot be negative")
    private int stockCount;

    public Book(){
    }
    public Book(UUID id, String title, int stockCount){
        super(id);
        this.title = title;
        this.stockCount = stockCount;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public int getStockCount(){
        return stockCount;
    }
    public void setStockCount(int stockCount){
        this.stockCount = stockCount;
    }
}
