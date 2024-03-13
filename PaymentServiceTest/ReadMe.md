1. User -> OrderServices
gets the orderId

2. User -> PaymentService
for this orderId, get the payment link.
give orderId to PaymentService.
Internally it will call OrderService to get the amount.
Now call the PaymentGateway with {orderId, amount}
and get the link.

3. User -> payment link
does the payment. 
gets redirected to? Callback URL

4. CallbackUrl calls the PaymentService (which calls PaymentGateway) 
for status of orderId.

5. PaymentGateway -> PaymentService via webhooks URL



APIs in PaymentService
1. createPaymentLink -> Important
2. getPaymentStatus -> commented code
3. handleWebhookEvent -> dry run