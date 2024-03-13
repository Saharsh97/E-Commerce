package com.scaler.paymentservicetest.services;

import com.razorpay.RazorpayException;

public interface PaymentService {
    String createPaymentLink(String orderId) throws Exception;

    String getPaymentStatus(String paymentId);
}
