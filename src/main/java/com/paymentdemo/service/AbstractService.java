package com.paymentdemo.service;

import com.paymentdemo.repository.CardRepository;
import com.paymentdemo.repository.CustomerRepository;
import com.paymentdemo.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractService
{
    /*
        Creating the customer repositories at the AbstractService level.
        This is because, due to us wanting to do extensive searches, all repositories will realistically need to be
        accessed by all Services. This makes them easily extendable as well.
     */
    @Autowired
    protected CustomerRepository customerRepo;
    @Autowired
    protected CardRepository cardRepo;
    @Autowired
    protected PurchaseRepository purchaseRepo;
}
