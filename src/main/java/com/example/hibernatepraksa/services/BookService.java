package com.example.hibernatepraksa.services;

import com.example.hibernatepraksa.entity.Author;
import com.example.hibernatepraksa.entity.Book;
import com.example.hibernatepraksa.entity.HibernateUtil;
import com.example.hibernatepraksa.entity.Review;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;


import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @PersistenceUnit
    private EntityManagerFactory emf;



    public Book getBook(int id){
        Book book;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            book = (Book) session.createQuery("from Book b LEFT JOIN FETCH b.reviews rev LEFT JOIN FETCH b.authors auth where b.id=:bookid").setParameter("bookid" , id).uniqueResult();
            session.getTransaction().commit();
        }
        return book;
    }

    public void postReview(int id, Review review){
        Book book;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            book = session.get(Book.class, id);
            book.getReviews().add(review);
            session.getTransaction().commit();

        }
    }

    public List<Book> getAll(){
        List<Book> books;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Book b LEFT JOIN FETCH b.reviews rev LEFT JOIN FETCH b.authors auth" , Book.class);
            books = query.getResultList();
            session.getTransaction().commit();

        }
        return books;
    }

    public void addBook(Book book){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        }
    }

    public void deleteBook(int id){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, id);
            session.delete(book);
            session.getTransaction().commit();

        }

    }
    public void updatePrice(int id, int price){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, id);
            book.setPrice(price);
            session.update(book);
            session.getTransaction().commit();

        }

    }

     public List<Book> sortBooksGreater(int price){
        List<Book> books;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            books = (List<Book>) session.createQuery("from Book b LEFT JOIN FETCH b.reviews rev LEFT JOIN FETCH b.authors auth where b.price>=:bookprice").setParameter("bookprice" , price).getResultList();
            session.getTransaction().commit();
        }
        return books;
    }

    public List<Book> sortBooksLess(int price){
        List<Book> books;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            books = (List<Book>) session.createQuery("from Book b LEFT JOIN FETCH b.reviews rev LEFT JOIN FETCH b.authors auth where b.price<=:bookprice").setParameter("bookprice" , price).getResultList();
            session.getTransaction().commit();
        }
        return books;
    }


}
