package com.example.library.controllers;

import com.example.library.DTO.BookDTO;
import com.example.library.entities.Author;
import com.example.library.entities.Book;
import com.example.library.entities.Genre;
import com.example.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    // Create a new book
    @PostMapping
    public Book createBook(@RequestBody BookDTO book) {
        return bookService.saveBook(book);
    }

    // Get all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Get a book by its ID
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // Update a book
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody BookDTO book) {
        // Set the book's ID to ensure the correct book is updated
        book.id = id;
        return bookService.saveBook(book);
    }

    // Delete a book by its ID
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    // Search for books by title
    @GetMapping("/search")
    public List<Book> searchBooksByTitle(@RequestParam("title") String title) {
        return bookService.searchBooksByTitle(title);
    }

    // Search for books by author
    @GetMapping("/searchByAuthor")
    public List<Book> searchBooksByAuthor(@RequestParam("authorId") Long authorId) {
        Author author = new Author();
        author.setId(authorId);
        return bookService.getBooksByAuthor(author);
    }

    // Search for books by genre
    @GetMapping("/searchByGenre")
    public List<Book> searchBooksByGenre(@RequestParam("genreId") Long genreId) {
        Genre genre = new Genre();
        genre.setId(genreId);
        return bookService.getBooksByGenre(genre);
    }
}

