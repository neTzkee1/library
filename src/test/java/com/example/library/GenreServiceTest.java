package com.example.library;

import com.example.library.entities.Genre;
import com.example.library.repo.GenreRepository;
import com.example.library.services.GenreService;
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
public class GenreServiceTest {

    @Autowired
    private GenreService genreService;

    @MockBean
    private GenreRepository genreRepository;

    @Test
    public void testCreateGenre() {
        Genre genre = new Genre();
        genre.setName("Fiction");
        genre.setDescription("A broad genre encompassing various types of fiction books");

        when(genreRepository.save(any(Genre.class))).thenReturn(genre);

        Genre savedGenre = genreService.saveGenre(genre);

        assertEquals("Fiction", savedGenre.getName());
        assertEquals("A broad genre encompassing various types of fiction books", savedGenre.getDescription());
    }

    @Test
    public void testGetGenreById() {
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Mystery");
        genre.setDescription("Books that involve solving mysteries and crimes");

        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));

        Genre retrievedGenre = genreService.getGenreById(1L);

        assertEquals(Optional.of(1L), retrievedGenre.getId());
        assertEquals("Mystery", retrievedGenre.getName());
        assertEquals("Books that involve solving mysteries and crimes", retrievedGenre.getDescription());
    }

    // Additional tests for update, delete, and more
}
