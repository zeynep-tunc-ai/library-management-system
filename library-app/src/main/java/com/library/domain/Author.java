package com.library.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "authors")
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
