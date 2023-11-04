package com.example.library.repo;

import com.example.library.entities.Author;
import com.example.library.entities.Book;
import com.example.library.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);
    List<Book> findByAuthor(Author author);
    List<Book> findByGenre(Genre genre);
}
