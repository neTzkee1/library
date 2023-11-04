package com.example.library;

import com.example.library.entities.Author;
import com.example.library.repo.AuthorRepository;
import com.example.library.services.AuthorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @MockBean
    private AuthorRepository authorRepository;

    @Test
    public void testCreateAuthor() {
        Author author = new Author();
        author.setName("John Doe");
        author.setBio("A sample biography");

        when(authorRepository.save(any(Author.class))).thenReturn(author);

        Author savedAuthor = authorService.saveAuthor(author);

        assertEquals("John Doe", savedAuthor.getName());
        assertEquals("A sample biography", savedAuthor.getBio());
    }

    @Test
    public void testGetAuthorById() {
        Author author = new Author();
        author.setId(1L);
        author.setName("Jane Smith");
        author.setBio("Another biography");

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Author retrievedAuthor = authorService.getAuthorById(1L);

        assertEquals(1L, retrievedAuthor.getId());
        assertEquals("Jane Smith", retrievedAuthor.getName());
        assertEquals("Another biography", retrievedAuthor.getBio());
    }

    // Additional tests for update, delete, and more
}

