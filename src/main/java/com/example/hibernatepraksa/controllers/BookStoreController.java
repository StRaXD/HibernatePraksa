package com.example.hibernatepraksa.controllers;

import com.example.hibernatepraksa.entity.Book;
import com.example.hibernatepraksa.entity.BookStore;
import com.example.hibernatepraksa.services.BookStoreService;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<BookStore> getStores(){
        return bookStoreService.getStores();
    }

    @GetMapping("/{id}/books")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Book> getBooks(@PathVariable ("id") int id){
        return bookStoreService.getBooks(id);
    }

    @PostMapping("{id}/add/book")
    @PreAuthorize("hasRole('ADMIN')")
    public void addBook(@PathVariable int id, @RequestBody Book book){
        bookStoreService.addBook(id, book);
    }

    @PutMapping("/{id}/{location}")
    @PreAuthorize("hasRole('ADMIN')")
    public void changePrice(@PathVariable int id, @PathVariable String location){
        bookStoreService.updateLocation(id, location);
    }

    @PutMapping("/add/{id}/{bookid}")
    @PreAuthorize("hasRole('RADMIN')")
    public void addExistingBook(@PathVariable int id, @PathVariable int bookid){
        bookStoreService.addExistingBook(id, bookid);
    }
}
