package com.paymentdemo.model;

import javax.persistence.*;

/**
 * A class that represents a payment card.
 * At the moment it only contains two columns in the database - cardId, and customerId.
 */
@Entity
@Table(name = "card")
public class Card {
    // The ID number of the card, as an integer. It will be viewed as a padded string.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;
    //The customer ID will be used to reference the customer that the card belongs to.
    private int customerId;
    // The status of the card - whether it is valid or not.
    private boolean isValid;

    /**
     * Default constructor. Takes in a customerId and sets it, designating the customer the card belongs to.
     * @param customerId The customer ID
     */
    public Card(int customerId)
    {
        this.customerId = customerId;

        // Since we will probably never "create" a new card that is automatically invalid, it doesn't make much sense
        // to have this as a parameter in a constructor.
        validate();
    }

    /**
     * No-args constructor. Not strictly necessary.
     */
    public Card()
    {

    }

    /**
     * Retrieves the card ID.
     * @return The card ID.
     */
    public int getCardId()
    {
        return cardId;
    }

    /**
     * A simple Getter that returns the customer ID. Note that we do not have an accompanying setter. This is because we won't be reassigning
     * a customer to a previously-used card ID (would cause all sorts of issues...)
     * @return The customer ID.
     */
    @Column(name = "customerId", nullable = false)
    public int getCustomerId()
    {
        return customerId;
    }

    /**
     * @return The status of the card ID - "valid" will be false if reported stolen for example. (note in this demo we don't handle any expiry logic)
     */
    @Column(name = "isValid", nullable = false)
    public boolean isValid()
    {
        return isValid;
    }

    public void setCardId(int cardId)
    {
        this.cardId = cardId;
    }

    public void invalidate()
    {
        this.isValid = false;
    }

    public void validate()
    {
        this.isValid = true;
    }


}
