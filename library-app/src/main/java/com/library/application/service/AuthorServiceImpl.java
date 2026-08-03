package com.library.application.service;

import com.library.application.repository.AuthorRepository;
import com.library.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service //İş kurallarının çalıştığı yer
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    //save():Veritabanına INSERT INTO sorgusu atar ve yeni yazarı veritabanına kaydeder
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author getAuthorById(UUID id) {
        return authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not founded"));
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author updateAuthor(UUID id, Author author) {
        Author foundAuthor = getAuthorById(id); //Yazar var mı konrtol ediyoruz
        foundAuthor.setFullName(author.getFullName()); //Bulunun yazarın adını soyadını değiştiriyoruz
        //save():ID zaten var olduğu için bu sefer UPDATE sorgusu atar ve günceller
        return authorRepository.save(foundAuthor);
    }

    @Override
    public void deleteAuthorById(UUID id) {
        Author authorToDelete = getAuthorById(id); //Yazar var mı kontrol ediyoruz
        authorRepository.deleteById(authorToDelete.getId());
    }

    @Override
    public Page<Author> getAllAuthorsPaged(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

}
