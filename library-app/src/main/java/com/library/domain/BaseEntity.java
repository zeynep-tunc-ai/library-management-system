package com.library.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) //ID'yi veri tabanında otomatik arttırır.
    private UUID id;

    public BaseEntity(){
    }
    public BaseEntity(UUID id){
        this.id = id;
    }
    public UUID getId(){
        return id;
    }
    public void setId(UUID id){
        this.id = id;
    }
}
