package com.paymentdemo.repository;

import com.paymentdemo.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This uses standard JpaRepository features, with no extra implemented functionality for now.
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer>{


}