package com.example.library.repo;

import com.example.library.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    // You can add custom query methods here if needed
//    List<Author> findByName(String name);
}
