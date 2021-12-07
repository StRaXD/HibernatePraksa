package com.example.hibernatepraksa.security;


import com.example.hibernatepraksa.entity.HibernateUtil;
import com.example.hibernatepraksa.entity.MyUserDetails;
import com.example.hibernatepraksa.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User loadedUser;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            loadedUser = (User) session.createQuery("from User where userName=:username").setParameter("username" , username).uniqueResult();
            session.getTransaction().commit();
        }
        return new MyUserDetails(loadedUser);
    }
}
