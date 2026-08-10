package com.library.dto;
//İstemci ile sunucu arasında veri taşımak için kullanılan Veri Taşıma Nesneleri
public class CreateAuthorRequest {
    private String fullName;

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
