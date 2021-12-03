package com.example.hibernatepraksa.services;

import com.example.hibernatepraksa.entity.Book;
import com.example.hibernatepraksa.entity.BookStore;
import com.example.hibernatepraksa.entity.HibernateUtil;
import com.example.hibernatepraksa.entity.Review;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

@Service
public class BookStoreService {

    public void addBook(int id , Book book) {
        BookStore store;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            store = session.get(BookStore.class, id);
            store.getBooks().add(book);
            System.out.println("Books: " + store.getBooks());
            session.getTransaction().commit();

        }
    }

    public List<Book> getBooks(int id){
        List<Book> books;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            BookStore store = session.get(BookStore.class, id);
            books = store.getBooks();
            session.getTransaction().commit();
        }
        return books;
    }
}