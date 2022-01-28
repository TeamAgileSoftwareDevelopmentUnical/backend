package asd.amazon.request;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PayPalConfirmPaymentRequest {
    private String paymentId;
    private String payerId;
    private Long customerId;
    private ArrayList<ProductInfo> productIds;
}
