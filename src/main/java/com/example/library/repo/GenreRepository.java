package com.example.library.repo;

import com.example.library.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    // You can add custom query methods here if needed
//    List<Genre> findByName(String name);
}
