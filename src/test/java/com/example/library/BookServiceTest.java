package com.example.library;

import com.example.library.DTO.BookDTO;
import com.example.library.entities.Author;
import com.example.library.entities.Book;
import com.example.library.entities.Genre;
import com.example.library.repo.BookRepository;
import com.example.library.services.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void testCreateBook() {
        Book book = new Book();
        book.setTitle("Sample Book");
        Author author = new Author();
        author.setId(1L);
        book.setAuthor(author);
        Genre genre = new Genre();
        genre.setId(1L);
        book.setGenre(genre);
        book.setPrice(19.99);
        book.setQuantityAvailable(10);

        BookDTO bookDTO = new BookDTO();
        bookDTO.title = "Sample Book";
        bookDTO.quantityAvailable = 10;
        bookDTO.price = 19.99;
        bookDTO.authorId = 1L;
        bookDTO.genreId = 1L;

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book savedBook = bookService.saveBook(bookDTO);

        assertEquals("Sample Book", savedBook.getTitle());
        assertEquals(Optional.of(1L), savedBook.getAuthor().getId());
        assertEquals(Optional.of(1L), savedBook.getGenre().getId());
        assertEquals(19.99, savedBook.getPrice(), 0.01);
        assertEquals(10, savedBook.getQuantityAvailable());
    }

    @Test
    public void testGetBookById() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Another Book");
        Author author = new Author();
        author.setId(2L);
        book.setAuthor(author);
        Genre genre = new Genre();
        genre.setId(3L);
        book.setGenre(genre);
        book.setPrice(24.99);
        book.setQuantityAvailable(5);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book retrievedBook = bookService.getBookById(1L);

        assertEquals(Optional.of(1L), retrievedBook.getId());
        assertEquals("Another Book", retrievedBook.getTitle());
        assertEquals(Optional.of(2L), retrievedBook.getAuthor().getId());
        assertEquals(Optional.of(3L), retrievedBook.getGenre().getId());
        assertEquals(24.99, retrievedBook.getPrice(), 0.01);
        assertEquals(5, retrievedBook.getQuantityAvailable());
    }

    // Additional tests for update, delete, search, and more
}

