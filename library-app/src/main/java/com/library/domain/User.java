package com.library.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {
    @NotBlank(message = "User name cannot be empty")
    private String fullName;
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;
    private String identityNumber;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING) // Enum değerini veritabanında doğrudan metin olarak tutar
    private Role role = Role.ROLE_USER; // Varsayılan rol Role.ROLE_USER olarak atar

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
    public Role getRole() {
        return this.role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //Kullanıcının yetkilerini spring security'ye haber verir
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
