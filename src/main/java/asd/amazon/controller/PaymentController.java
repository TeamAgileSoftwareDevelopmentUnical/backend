package asd.amazon.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import asd.amazon.entity.Batch;
import asd.amazon.entity.CustomerAccount;
import asd.amazon.entity.Product;
import asd.amazon.entity.Purchase;
import asd.amazon.repository.BatchRepository;
import asd.amazon.repository.CustomerAccountRepository;
import asd.amazon.repository.ProductRepository;
import asd.amazon.repository.PurchaseRepository;
import asd.amazon.request.CreatePayment;
import asd.amazon.request.PayPalConfirmPaymentRequest;
import asd.amazon.request.PaymentRequest;
import asd.amazon.request.ProductInfo;
import asd.amazon.responses.CreatePaymentResponse;
import asd.amazon.responses.PaymentConfirmResponse;
import asd.amazon.responses.PaypalPaymentResponse;
import asd.amazon.service.PaymentService;
import asd.amazon.utils.CommonConstant;

@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {RequestMethod.PATCH,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST})
@RestController
@RequestMapping(CommonConstant.PAYMENT_ROOT)
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerAccountRepository customerAccountRepository;
    @Autowired
    private BatchRepository batchRepository;

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

    @Transactional
    @PostMapping(CommonConstant.PAYMENT_SUCCESS)
    public ResponseEntity<PaymentConfirmResponse> successPayment(@RequestBody PayPalConfirmPaymentRequest request){
        final double[] amount = new double[1];
        PaymentConfirmResponse response = null;
        try {
            Payment payment = paymentService.executePayment(request.getPaymentId(), request.getPayerId());
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")){
                response = new PaymentConfirmResponse();
                response.setStatus(payment.getState());
                response.setPaymentID(payment.getId());

                //PaymentConfirmResponse finalResponse = response;
                payment.getTransactions().forEach(transaction -> {
                    if (!transaction.getAmount().getTotal().isEmpty()){
                        amount[0] = (Double.parseDouble(transaction.getAmount().getTotal()));
                    }

                });
                response.setAmount(amount[0]);
                // add purchase information
                addPurchaseInformation(request.getCustomerId(),request.getProductIds());
                return ResponseEntity.ok().body(response);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    private void addPurchaseInformation(Long customerId, ArrayList<ProductInfo> productInfos) {
        productInfos.forEach(productInfo -> {
            Purchase purchase = new Purchase();

            Product product = productRepository.getById(productInfo.getProductId());

            // update product quantity
            Batch batch = product.getBatch();
            batch.setAvailableQuantity(batch.getAvailableQuantity()-productInfo.getQuantity());
            batchRepository.save(batch);

            CustomerAccount customer = customerAccountRepository.findCustomerAccountsById(customerId);

            purchase.setProduct(product);
            purchase.setCustomer(customer);
            purchase.setPaymentMethod("PayPal");
            purchase.setProductQuantity(productInfo.getQuantity());
            purchase.setShippingAddress(customer.getShippingAddress());
            purchase.setDate(LocalDateTime.now());

            purchaseRepository.save(purchase);

        });
    }

    @Transactional
    @PostMapping(CommonConstant.PAYMENT_CREATE_STRIPE)
    public ResponseEntity<CreatePaymentResponse> stripePaymentCreate(@RequestBody CreatePayment createPayment){
        CreatePaymentResponse response = paymentService.stripePaymentCreate(createPayment);
        if (response != null){
            addPurchaseInformation(createPayment.getCustomerId(),createPayment.getProductIds());
        }
        return ResponseEntity.ok().body(response);
    }
}
