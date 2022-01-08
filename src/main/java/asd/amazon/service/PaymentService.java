package asd.amazon.service;

import asd.amazon.request.PaymentRequest;
import com.paypal.api.payments.Payment;

public interface PaymentService {
    Payment createPayment(PaymentRequest request);
    Payment executePayment(String paymentId, String payerId);
}
