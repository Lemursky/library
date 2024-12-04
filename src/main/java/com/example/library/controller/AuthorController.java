package com.example.library.controller;

import com.example.library.entity.Author;
import com.example.library.exception.IncorrectData;
import com.example.library.exception.NoSuchDataException;
import com.example.library.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/author/{id}")
    public Author getAuthorById(@PathVariable Integer id) {
        Author author = authorRepository.findById(id).orElse(null);
        if(author == null) {
            throw new NoSuchDataException("The object with ID = " + id + " not found in Database");
        }
        return author;
    }

    @PostMapping("/author_add")
    public Author addAuthor(@RequestBody Author author){
        return authorRepository.save(author);
    }

    @PatchMapping("/author_update")
    public void updateAuthor(@RequestBody Author author) {
        authorRepository.save(author);
    }

    @DeleteMapping("/author_delete/{id}")
    public void deleteAuthor(@PathVariable Integer id) {
        authorRepository.deleteById(id);
    }

}