package asd.amazon.request;

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
}
