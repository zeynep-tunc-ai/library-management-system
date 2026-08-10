package com.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonIgnoreProperties("books") //Yazar-Kitap arasındaki sonsuz JSON döngüsünü engeller
    private Author author;

    public Book(){
    }
    public Book(UUID id, String title, int stockCount, Author author) {
        super(id);
        this.title = title;
        this.stockCount = stockCount;
        this.author = author;
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
    public Author getAuthor(){
        return author;
    }
    public void setAuthor(Author author){
        this.author = author;
    }
}
