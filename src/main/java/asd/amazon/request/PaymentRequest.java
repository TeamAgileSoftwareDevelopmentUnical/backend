package asd.amazon.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentRequest {
    private double price;
    private String currency;
    private String method;
    private String intent;
    private String description;
    private String successURL;
    private String cancelURL;
}
