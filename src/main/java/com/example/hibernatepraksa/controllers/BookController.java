package com.example.hibernatepraksa.controllers;

import com.example.hibernatepraksa.entity.*;
import com.example.hibernatepraksa.services.BookService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Book getBook(@PathVariable("id") int id){
        return bookService.getBook(id);
    }

    @GetMapping("/get/all")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Book> getBook(){
        return bookService.getAll();
    }

    @PostMapping("/review/add/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public void postReview(@PathVariable("id") int id , @RequestBody Review review) {
        bookService.postReview(id, review);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public void postReview(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBook(@PathVariable int id){
        bookService.deleteBook(id);
    }

    @PutMapping("/price/{id}/{price}")
    @PreAuthorize("hasRole('ADMIN')")
    public void changePrice(@PathVariable int id, @PathVariable int price){
        bookService.updatePrice(id, price);
    }

    @GetMapping("/sort/greater/{price}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Book> sortBooksGreater(@PathVariable int price){
        return bookService.sortBooksGreater(price);
    }

    @GetMapping("/sort/less/{price}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Book> sortBooksLess(@PathVariable int price){
        return bookService.sortBooksLess(price);
    }
}
