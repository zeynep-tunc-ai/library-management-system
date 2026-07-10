package com.library.domain;

public class Book {
    private Long id;
    private String title;
    private int stockCount;

    public Book(){
    }
    public Book(Long id, String title, int stockCount){
        this.id = id;
        this.title = title;
        this.stockCount = stockCount;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
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
