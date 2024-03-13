package com.scaler.paymentservicetest.configs;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfig {

    @Value("${razorpay.key_id}")
    private String razorpayKeyId;
    @Value("${razorpay.key_secret}")
    private String razorpayKeySecret;

    @Bean
    public RazorpayClient createRazorpayClient() throws RazorpayException {
        // i need secret key and secret id. How will I get it?
        // from Razorpay dashboard.
        // similar to setting up gmail for my app service.

        // you can declare this in Env properties
        return new RazorpayClient(
                razorpayKeyId,
                razorpayKeySecret
        );
        // now can I autowire this in PaymentService.
    }
}
