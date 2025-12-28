package com.rahulsmgv.internalworkingofspringboot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "payment.provider", havingValue = "stripe")
public class StripePaymentService implements PaymentService{
    public String pay(){
        String payment = "Stripe payment";
        System.out.println("Payment from "+ payment);
        return  payment;
    }
}