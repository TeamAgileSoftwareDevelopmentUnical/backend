package asd.amazon.service;

import asd.amazon.request.CreatePayment;
import asd.amazon.request.PaymentRequest;
import asd.amazon.responses.CreatePaymentResponse;
import com.paypal.api.payments.Payment;

public interface PaymentService {
    Payment createPayment(PaymentRequest request);
    Payment executePayment(String paymentId, String payerId);
    CreatePaymentResponse stripePaymentCreate(CreatePayment createPayment);
}
