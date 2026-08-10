package com.library.dto;

import java.util.UUID;

public class CreateBookRequest {
    private String title;
    private int stockCount;
    private UUID authorId;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getStockCount() {
        return stockCount;
    }
    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }
    public UUID getAuthorId() {
        return authorId;
    }
    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }
}
