package controllers;

import models.Payable;

public class PaymentController implements Payable {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + "...");
        System.out.println("Payment successful!");
    }
}
