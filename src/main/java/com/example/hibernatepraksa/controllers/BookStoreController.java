package com.example.hibernatepraksa.controllers;

import com.example.hibernatepraksa.entity.Book;
import com.example.hibernatepraksa.entity.BookStore;
import com.example.hibernatepraksa.services.BookStoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class BookStoreController {

    BookStoreService bookStoreService;

    public BookStoreController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @GetMapping("/get/all")
    public List<BookStore> getStores(){
        return bookStoreService.getStores();
    }

    @GetMapping("/{id}/books")
    public List<Book> getBooks(@PathVariable ("id") int id){
        return bookStoreService.getBooks(id);
    }

    @PostMapping("{id}/add/book")
    public void addBook(@PathVariable int id, @RequestBody Book book){
        bookStoreService.addBook(id, book);
    }

    @PutMapping("/{id}/{location}")
    public void changePrice(@PathVariable int id, @PathVariable String location){
        bookStoreService.updateLocation(id, location);
    }

    @PutMapping("/add/{id}/{bookid}")
    public void addExistingBook(@PathVariable int id, @PathVariable int bookid){
        bookStoreService.addExistingBook(id, bookid);
    }
}
