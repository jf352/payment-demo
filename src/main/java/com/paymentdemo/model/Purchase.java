package com.paymentdemo.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * A class that represents a purchase from a customer using a specific card registered to them.
 */
@Entity
@Table(name = "purchase")
public class Purchase {
    // The ID number of the purchase. Will be a simple ID.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseId;
    //The date of the purchase.
    private Date purchaseDate;
    // The name of the shop being bought from ("shop" being any generic vendor)
    private String shop;
    // The cost of the shop
    private int cost;
    // The customer the purchase belongs to
    private int customerId;
    // The card the purchase belongs to
    private int cardId;

    /**
     * Stores all the details in a Purchase table.
     *
     * @param shop The shop the purchase occurred at.
     * @param cost The cost of the purchase.
     * @param customerId The customer who initiated the purchase.
     * @param cardId The card on which the purchase was made.
     */
    public Purchase(Date purchaseDate, String shop, int cost, int customerId, int cardId)
    {
        setShop(shop);
        setCost(cost);
        setCustomerId(customerId);
        setCardId(cardId);
        setPurchaseDate(purchaseDate);
    }

    /**
     * No-args constructor. Not strictly necessary.
     */
    public Purchase()
    {

    }

    @Column(name = "purchaseId", nullable = false)
    public int getPurchaseId()
    {
        return purchaseId;
    }

    @Column(name = "purchaseDate", nullable = false)
    public Date getPurchaseDate()
    {
        return purchaseDate;
    }

    @Column(name = "shop", nullable = false)
    public String getShop() {
        return shop;
    }

    @Column(name = "cost", nullable = false)
    public int getCost() {
        return cost;
    }

    @Column(name = "customerId", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    @Column(name = "cardId", nullable = false)
    public int getCardId() {
        return cardId;
    }

    public void setPurchaseId(int purchaseId)
    {
        this.purchaseId = purchaseId;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
}
