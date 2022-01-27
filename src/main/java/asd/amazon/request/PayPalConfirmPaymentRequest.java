package asd.amazon.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
