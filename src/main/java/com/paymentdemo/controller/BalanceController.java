package com.paymentdemo.controller;

import com.paymentdemo.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/demo")
public class BalanceController extends AbstractController<Integer> {

    @Autowired
    private BalanceService balanceService;

    /**
     * Maps to the BalanceService "getByCustomer" method, which returns the balance of a customer.
     * @param customerId ID of the customer.
     * @return An object representation of the balance (simple string).
     */
    @GetMapping("/balance")
    public ResponseEntity<Integer> get(@RequestParam("customerId") Integer customerId) {
        Integer balance = balanceService.getByCustomer(customerId);
        return constructResponseEntity(balance);
    }
}
