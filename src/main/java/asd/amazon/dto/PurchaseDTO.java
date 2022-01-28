package asd.amazon.dto;

import java.time.LocalDateTime;

import asd.amazon.entity.CustomerAccount;
import lombok.Data;

@Data
public class PurchaseDTO {

    private Long id;

    private CustomerAccount customer;

    private LocalDateTime date;

    private ProductDTO soldProduct;

    private Integer productQuantity;

    private String shippingAddress;

    private String paymentMethod;

    private Float total;
}
