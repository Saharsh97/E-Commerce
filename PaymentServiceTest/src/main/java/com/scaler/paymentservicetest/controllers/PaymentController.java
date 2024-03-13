package com.scaler.paymentservicetest.controllers;

import com.razorpay.Webhook;
import com.scaler.paymentservicetest.dtos.CreatePaymentLinkRequestDTO;
import com.scaler.paymentservicetest.services.PaymentService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public String createPaymentLink(@RequestBody CreatePaymentLinkRequestDTO requestDTO) throws Exception {
        // what will this do? call PaymentGateway.
        // will I call only one type of Gateway? could be multiple in future
        // interface service.
        return paymentService.createPaymentLink(requestDTO.getOrderId());
    }

    @PostMapping("/webhook")
    public void handleWebhookEvent(@RequestBody Map<String, Object> webhook){
        System.out.println(webhook.toString());
    }
}
















