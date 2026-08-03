package com.library.application.repository;

import com.library.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
    User findByEmail(String email);
    //Veritabanında bu e-posta adresine sahip kullanıcı var mı yok mu kontrol eder
    boolean existsByEmail(String email);
}
