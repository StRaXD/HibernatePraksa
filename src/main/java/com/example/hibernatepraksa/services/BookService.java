package com.example.hibernatepraksa.services;

import com.example.hibernatepraksa.entity.Book;
import com.example.hibernatepraksa.entity.HibernateUtil;
import com.example.hibernatepraksa.entity.Review;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

@Service
public class BookService {

    public Book getBook(int id){
        Book tempInstructorDetail;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            tempInstructorDetail =
                    session.get(Book.class, id);

            // commit transaction
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
        List<Book> deptEmployees;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Book ");
            deptEmployees = query.getResultList();
            session.getTransaction().commit();

        }
        return deptEmployees;
    }

    public void addBook(Book book){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.save(book);

            transaction.commit();
        }
    }

}
