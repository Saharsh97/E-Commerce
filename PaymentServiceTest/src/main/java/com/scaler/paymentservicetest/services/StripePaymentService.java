package com.scaler.paymentservicetest.services;

public class StripePaymentService implements PaymentService{
    // this will work by calling the packages of Stripe APIs etc.
    @Override
    public String createPaymentLink(String orderId) {
        return null;
    }

    @Override
    public String getPaymentStatus(String paymentId) {
        return null;
    }
}
