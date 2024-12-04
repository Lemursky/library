package com.example.library.controller;

import com.example.library.entity.Books;
import com.example.library.exception.IncorrectData;
import com.example.library.exception.NoSuchDataException;
import com.example.library.repository.BooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BooksController {

    private final BooksRepository booksRepository;

    @GetMapping("/books")
    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    @GetMapping("/book/{id}")
    public Books getBookById(@PathVariable Integer id) {
        Books books = booksRepository.findById(id).orElse(null);
        if(books == null) {
            throw new NoSuchDataException("The object with ID = " + id + " not found in Database");
        }
        return books;
    }

    @PostMapping("/book_add")
    public Books addBook(@RequestBody Books books){
        return booksRepository.save(books);
    }

    @PatchMapping("/book_update")
    public void updateBook(@RequestBody Books books) {
        booksRepository.save(books);
    }

    @DeleteMapping("/book_delete/{id}")
    public void deleteBook(@PathVariable Integer id) {
        booksRepository.deleteById(id);
    }

}