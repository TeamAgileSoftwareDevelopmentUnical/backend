package asd.amazon.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PurchaseResponse {
    private String productName;
    private String productDescription;
    private Float productPrice;
    private Integer productQuantity;
    private Float totalPrice;
    private String sellerUsername;
    private LocalDateTime purchaseDate;
    private String paymentMethod;
    private String shippingAddress;
    private String photo;
}
