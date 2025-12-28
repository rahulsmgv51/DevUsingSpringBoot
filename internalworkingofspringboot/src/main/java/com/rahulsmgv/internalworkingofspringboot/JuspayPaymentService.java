package com.rahulsmgv.internalworkingofspringboot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "payment.provider", havingValue = "juspay")
public class JuspayPaymentService implements PaymentService{
    public String pay(){
        String payment = "jus pay payment";
        System.out.println("Payment from "+ payment);
        return  payment;
    }
}
