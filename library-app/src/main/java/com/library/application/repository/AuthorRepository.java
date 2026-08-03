package com.library.application.repository;

import com.library.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository //Veritabanı işlemleri
public interface AuthorRepository extends JpaRepository<Author, UUID>{
    Author findByFullName(String fullname);
}
