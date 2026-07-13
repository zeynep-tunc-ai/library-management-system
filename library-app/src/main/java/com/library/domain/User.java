package com.library.domain;

import java.util.UUID;

public class User extends BaseEntity{
    private String fullName;
    private String email;
    private String identityNumber;

    public User(){
    }
    public User(UUID id, String fullName, String email, String identityNumber){
        super(id);
        this.fullName = fullName;
        this.email = email;
        this.identityNumber = identityNumber;
    }
    public String getFullName(){
        return fullName;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getIdentityNumber(){
        return identityNumber;
    }
    public void setIdentityNumber(String identityNumber){
        this.identityNumber = identityNumber;
    }
}
