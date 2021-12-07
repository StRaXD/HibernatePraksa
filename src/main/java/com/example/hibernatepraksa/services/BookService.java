package com.example.hibernatepraksa.services;

import com.example.hibernatepraksa.entity.Book;
import com.example.hibernatepraksa.entity.HibernateUtil;
import com.example.hibernatepraksa.entity.Review;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Service
public class BookService {

    @PersistenceUnit
    private EntityManagerFactory emf;

    public Book getBook(int id){
        Book tempInstructorDetail;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            tempInstructorDetail = session.get(Book.class, id);
            session.getTransaction().commit();

        }
        return tempInstructorDetail;
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
            Query query = session.createQuery("from Book b LEFT JOIN FETCH b.reviews rev" , Book.class);
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

     public List<Book> sortBooks(int price){
        List<Book> books;
        EntityManager em = emf.createEntityManager();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query q = em.createNativeQuery("SELECT * FROM Book WHERE price>" + price);
            books = q.getResultList();
            session.getTransaction().commit();

        }
        return books;
    }


}
