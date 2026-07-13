package com.library.domain;

import java.util.UUID;

public class Author extends BaseEntity{
    private String fullName;

    public Author(){
    }
    public Author(UUID id, String fullName){
        super(id);
        this.fullName = fullName;
    }
    public String getFullName(){
        return fullName;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
}
