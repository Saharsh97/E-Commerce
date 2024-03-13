package com.scaler.paymentservicetest.services;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class RazorPayPaymentService implements PaymentService{

    private RazorpayClient razorpayClient;

    @Autowired
    public RazorPayPaymentService(RazorpayClient razorpayClient){
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String createPaymentLink(String orderId) throws Exception {
        // code from documentation:
        // https://github.com/razorpay/razorpay-java/blob/master/documents/paymentLink.md
        // explain code.
        JSONObject paymentLinkRequest = new JSONObject();
        // notes on Float, python.
        // computer stores approximations.
        // 1.000000000000001 == 1.
        // 24.401 Billion -> 24.400 billion. loss.

        // so Razorpay only accepts integers.
        // 99.99 -> put it as 9999.
        // 10.01 -> put it as 1001
        // this is Rs.10
        paymentLinkRequest.put("amount",1000);
        paymentLinkRequest.put("currency","INR");
        // this is false, comment below.
        paymentLinkRequest.put("accept_partial",false);
//        paymentLinkRequest.put("first_min_partial_amount",100);

        // this is Epoch. How many ms have happened since 1st Jan 1970.
        paymentLinkRequest.put("expire_by",System.currentTimeMillis() + (15*60*1000));
        paymentLinkRequest.put("reference_id",orderId);
        paymentLinkRequest.put("description","Payment for order number " + orderId);


        // Order order = orderService.getOrderDetails(orderId)
        // String customerName = order.getUser().getName();
        // String contact = order.getUser().getMobileNumber();
        JSONObject customer = new JSONObject();
        customer.put("name","+917827569029");
        customer.put("contact","Saharsh Singh");
        customer.put("email","saharsh.singh_1@scaler.com");
        paymentLinkRequest.put("customer",customer);

        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        notes.put("phone","IPhone 15");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://google.com");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        return payment.get("short_url");
    }

    @Override
    public String getPaymentStatus(String paymentId) {
        // go to DB
        // check status of paymentId
        // if not present.
        //      call razorpayClient.getStatus()
        //      store the response in DB
        // return the response.
        return null;
    }
}
