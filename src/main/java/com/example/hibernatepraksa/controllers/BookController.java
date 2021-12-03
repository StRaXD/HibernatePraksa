package com.example.hibernatepraksa.controllers;

import com.example.hibernatepraksa.entity.*;
import com.example.hibernatepraksa.services.BookService;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
    public Book getBook(@PathVariable("id") int id){
        return bookService.getBook(id);
    }

    @GetMapping("/get/all")
    public List<Book> getBook(){
        return bookService.getAll();
    }

    @PostMapping("/review/add/{id}")
    public void postReview(@PathVariable("id") int id , @RequestBody Review review) {
        bookService.postReview(id, review);
    }

    @PostMapping("/add")
    public void postReview(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable int id){
        bookService.deleteBook(id);
    }

    @PutMapping("/price/{id}/{price}")
    public void changePrice(@PathVariable int id, @PathVariable int price){
        bookService.updatePrice(id, price);
    }


}
