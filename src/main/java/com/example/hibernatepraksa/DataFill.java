package com.example.hibernatepraksa;

import com.example.hibernatepraksa.entity.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataFill {
    @Autowired
    PasswordEncoder passwordEncoder;
    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        Customer customer = new Customer("Strahinja", "Kovacevic", "strahinja@gmail.com");
        Customer customer1 = new Customer("Marko", "Slavkovic", "marko@gmail.com");
        Customer customer2 = new Customer("Dusan", "Ignjatovic", "dusan@gmail.com");
        Customer customer3 = new Customer("Igor", "Majstorovic", "igor@gmail.com");
        Customer customer4 = new Customer("Kristina", "Nenadic", "krisitna@gmail.com");
        Book book = new Book("Metro 2033", 750);
        Book book1 = new Book("The Stranger", 550);
        Book book2 = new Book("Bela Griva", 970);
        Book book3 = new Book("Rat i Mir", 800);
        Book book4 = new Book("Mali Princ", 250);
        Book book5 = new Book("Pop Ćira i pop Spira", 170);
        User strax = new User("strax", passwordEncoder.encode("password"),true,"ROLE_USER");

        BookStore store = new BookStore("Vulkan", "Beograd", "Terazije");

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.save(strax);
            session.save(customer);
            session.save(customer1);
            session.save(customer2);
            session.save(customer3);
            session.save(customer4);
            session.save(book);
            session.save(book1);
            session.save(book2);
            session.save(book3);
            session.save(book4);
            session.save(book5);
            session.save(store);


            transaction.commit();
        }
    }
}
