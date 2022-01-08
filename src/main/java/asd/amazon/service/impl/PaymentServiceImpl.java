package asd.amazon.service.impl;

import asd.amazon.request.PaymentRequest;
import asd.amazon.service.PaymentService;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private APIContext context;

    @Override
    public Payment createPayment(PaymentRequest request) {
        try {
            Amount amount = new Amount();
            amount.setCurrency(request.getCurrency());
            amount.setTotal(String.format("%.2f",BigDecimal.valueOf(request.getPrice())
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue()));

            Transaction transaction = new Transaction();
            transaction.setDescription(request.getDescription());
            transaction.setAmount(amount);

            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);

            Payer payer = new Payer();
            payer.setPaymentMethod(request.getMethod());

            Payment payment = new Payment();
            payment.setIntent(request.getIntent());
            payment.setPayer(payer);
            payment.setTransactions(transactions);

            RedirectUrls redirectUrls = new RedirectUrls();
            redirectUrls.setCancelUrl(request.getCancelURL());
            redirectUrls.setReturnUrl(request.getSuccessURL());
            payment.setRedirectUrls(redirectUrls);

            return payment.create(context);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public Payment executePayment(String paymentId, String payerId) {
        try {
            Payment payment = new Payment();
            payment.setId(paymentId);
            PaymentExecution paymentExecute = new PaymentExecution();
            paymentExecute.setPayerId(payerId);
            return payment.execute(context, paymentExecute);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
