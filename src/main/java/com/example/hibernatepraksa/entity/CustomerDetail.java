package com.example.hibernatepraksa.entity;

import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
public class CustomerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private String zipCode;
    private String cardNumber;

    @OneToOne(mappedBy = "customerDetail",cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Customer customer;

    public CustomerDetail() {
    }

    public CustomerDetail(String address, String zipCode, String cardNumber) {
        this.address = address;
        this.zipCode = zipCode;
        this.cardNumber = cardNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "CustomerDetail{" +
                "address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
