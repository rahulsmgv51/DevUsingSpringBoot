package com.rahulsmgv.internalworkingofspringboot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "payment.provider", havingValue = "razorpay")
public class RazorpayPaymentService implements PaymentService{
    public String pay(){
        String payment = "RazorPay payment";
        System.out.println("Payment from "+ payment);
        return  payment;
    }
}
