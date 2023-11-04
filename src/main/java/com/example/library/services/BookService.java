package com.example.library.services;

import com.example.library.DTO.BookDTO;
import com.example.library.entities.Author;
import com.example.library.entities.Book;
import com.example.library.entities.Genre;
import com.example.library.repo.AuthorRepository;
import com.example.library.repo.BookRepository;
import com.example.library.repo.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    public Book saveBook(BookDTO book) {
        Author author = authorRepository.findById(book.authorId).orElse(null);
        Genre genre = genreRepository.findById(book.authorId).orElse(null);
        System.out.println(author.getBio());

        if (author == null || genre == null) {
            // Handle the case where the specified Author or Genre doesn't exist
            // You can throw an exception or handle it according to your application's logic
            return null; // or throw an exception
        }
        Book newBook = new Book();
        // Set the retrieved Author and Genre on the Book entity
        newBook.setAuthor(author);
        newBook.setGenre(genre);
        newBook.setPrice(book.price);
        newBook.setTitle(book.title);
        newBook.setQuantityAvailable(book.quantityAvailable);

        return bookRepository.save(newBook);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }

    public List<Book> getBooksByAuthor(Author author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBooksByGenre(Genre genre) {
        return bookRepository.findByGenre(genre);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}

