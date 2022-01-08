package asd.amazon.controller;


import asd.amazon.request.PayPalConfirmPaymentRequest;
import asd.amazon.request.PaymentRequest;
import asd.amazon.responses.PaymentConfirmResponse;
import asd.amazon.responses.PaypalPaymentResponse;
import asd.amazon.service.PaymentService;
import asd.amazon.utils.CommonConstant;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {RequestMethod.PATCH,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST})
@RestController
@RequestMapping(CommonConstant.PAYMENT_ROOT)
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping(CommonConstant.PAYMENT_PAY)
    public ResponseEntity<PaypalPaymentResponse> payment(@RequestBody PaymentRequest request){
        PaypalPaymentResponse response = null;
        try {
            Payment payment = paymentService.createPayment(request);
            for (Links links: payment.getLinks()) {
                if (links.getRel().equals("approval_url")){
                    response = new PaypalPaymentResponse();
                    response.setStatus(true);
                    response.setUrl(links.getHref());
                    return ResponseEntity.ok().body(response);
                }
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(CommonConstant.PAYMENT_CANCEL)
    public String cancelPayment(){
        return "Payment Cancel";
    }

    @PostMapping(CommonConstant.PAYMENT_SUCCESS)
    public ResponseEntity<PaymentConfirmResponse> successPayment(@RequestBody PayPalConfirmPaymentRequest request){
        PaymentConfirmResponse response = null;
        try {
            Payment payment = paymentService.executePayment(request.getPaymentId(), request.getPayerId());
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")){
                response = new PaymentConfirmResponse();
                response.setStatus(payment.getState());
                return ResponseEntity.ok().body(response);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }
}
