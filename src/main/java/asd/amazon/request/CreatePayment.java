package asd.amazon.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatePayment {
    private String description;
    private Double amount;
    private String stripeToken;
    private String stripeEmail;
    private Long customerId;
    private ArrayList<ProductInfo> productIds;
}
