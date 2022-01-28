package asd.amazon.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asd.amazon.request.CreatePayment;
import asd.amazon.request.PaymentRequest;
import asd.amazon.responses.CreatePaymentResponse;
import asd.amazon.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private APIContext context;

    @Override
    public Payment createPayment(PaymentRequest request) {
        try {
            Amount amount = new Amount();
            // System.out.println(request.getPrice());
            // System.out.println(request.getCurrency());
            amount.setCurrency(request.getCurrency());
            amount.setTotal(String.format("%.2f",BigDecimal.valueOf(request.getPrice())
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue()).replace(",", "."));
            // System.out.println("Builded amount:\n"+amount);

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

    @Override
    public CreatePaymentResponse stripePaymentCreate(CreatePayment createPayment) {
        CreatePaymentResponse response = null;
        try {
            Stripe.apiKey = "sk_test_51KGA3WAw2GzsVNVa86BxdFcLiQRnFLDYwiFenCVcGOfhbXl5C8x5yDd2bNnHWTGakgAYduAcoZTJiePJnpsgqehY00NDLJO27W";
            ChargeCreateParams params =
                    ChargeCreateParams.builder()
                            .setAmount((long) (createPayment.getAmount()*100L))
                            .setCurrency("EUR")
                            .setDescription(createPayment.getDescription())
                            .setSource(createPayment.getStripeToken())
                            .build();

            Charge charge = Charge.create(params);
            if (charge.getStatus().equals("succeeded")){
                response = new CreatePaymentResponse();
                response.setStatus(charge.getStatus());
                response.setAmount(charge.getAmount());
                response.setPaymentID(charge.getId());
                return response;
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return response;
    }
}
