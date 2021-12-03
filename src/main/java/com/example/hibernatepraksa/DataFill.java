package com.example.hibernatepraksa;

import com.example.hibernatepraksa.entity.Book;
import com.example.hibernatepraksa.entity.BookStore;
import com.example.hibernatepraksa.entity.Customer;
import com.example.hibernatepraksa.entity.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataFill {
    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        Customer customer = new Customer("Strahinja", "Kovacevic", "strahinja@gmail.com");
        Customer customer1 = new Customer("Marko", "Slavkovic", "marko@gmail.com");
        Customer customer2 = new Customer("Dusan", "Ignjatovic", "dusan@gmail.com");
        Customer customer3 = new Customer("Igor", "Majstorovic", "igor@gmail.com");
        Customer customer4 = new Customer("Kristina", "Nenadic", "krisitna@gmail.com");
        Book book = new Book("Metro 2033", 750);
        BookStore store = new BookStore("Vulkan", "Beograd", "Terazije");

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // savethe student objects
            session.save(customer);
            session.save(customer1);
            session.save(customer2);
            session.save(customer3);
            session.save(customer4);
            session.save(book);
            session.save(store);
            // commit transaction
            transaction.commit();
        }
    }
}
