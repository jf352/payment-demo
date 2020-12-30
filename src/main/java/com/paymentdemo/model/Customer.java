package com.paymentdemo.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * A class that represents a payment card.
 * At the moment it only contains two columns in the database - cardId, and customerId.
 */
@Entity
@Table(name = "customer")
public class Customer {
    // The ID number of the card, as an integer. It will be viewed as a padded string.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    //The birth date of the customer.
    private Date birthDate;
    // The first name of the customer.
    private String firstName;
    // The last name of the customer.
    private String lastName;

    /**
     * Default constructor. Takes in a customerId and sets it, designating the customer the card belongs to.
     * @param firstName - The first name of the customer.
     * @param lastName - The surname of the customer.
     * @param birthDate - A date object representing the birthdate of the customer.
     */
    public Customer(String firstName, String lastName, Date birthDate)
    {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
    }

    /**
     * No-args constructor. Not strictly necessary.
     */
    public Customer()
    {

    }

    @Column(name = "customerId", nullable = false)
    public int getCustomerId()
    {
        return customerId;
    }

    @Column(name = "birthDate", nullable = false)
    public Date getBirthDate()
    {
        return birthDate;
    }

    @Column(name = "firstName", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "lastName", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }


}
