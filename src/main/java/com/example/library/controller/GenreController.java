package com.example.library.controller;

import com.example.library.entity.Genre;
import com.example.library.exception.NoSuchDataException;
import com.example.library.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GenreController {

    private final GenreRepository genreRepository;

    @GetMapping("/genres")
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @GetMapping("/genre/{id}")
    public Genre getGenreById(@PathVariable Integer id) {
        Genre genre = genreRepository.findById(id).orElse(null);
        if(genre == null) {
            throw new NoSuchDataException("The object with ID = " + id + " not found in Database");
        }
        return genre;
    }

    @PostMapping("/genre_add")
    public Genre addGenre(@RequestBody Genre genre){
        return genreRepository.save(genre);
    }

    @PatchMapping("/genre_update")
    public void updateGenre(@RequestBody Genre genre) {
        genreRepository.save(genre);
    }

    @DeleteMapping("/genre_delete/{id}")
    public void deleteGenre(@PathVariable Integer id) {
        genreRepository.deleteById(id);
    }
}
