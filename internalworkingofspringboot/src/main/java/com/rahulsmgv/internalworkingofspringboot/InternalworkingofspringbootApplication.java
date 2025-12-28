package com.rahulsmgv.internalworkingofspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InternalworkingofspringbootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(InternalworkingofspringbootApplication.class, args);
	}

//	//Create Object and it impl show the tight coupling
//	private JuspayPaymentService paymentService = new JuspayPaymentService();

	// Constructor Dependency injection.
	private final PaymentService paymentService;
	public InternalworkingofspringbootApplication(PaymentService paymentService){
		this.paymentService= paymentService;
	}

	// Field Injection
//	@Autowired
//	private JuspayPaymentService paymentService;

	@Override
	public void run(String... args) throws Exception {
		String payment = paymentService.pay();
		System.out.println("Payment done : "+payment);
	}
}