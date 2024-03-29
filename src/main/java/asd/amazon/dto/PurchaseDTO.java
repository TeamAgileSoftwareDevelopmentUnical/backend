package asd.amazon.dto;

import asd.amazon.entity.CustomerAccount;
import asd.amazon.responses.ProductResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PurchaseDTO {

    private Long id;

    private CustomerAccount customer;

    private LocalDateTime date;

    private ProductResponse soldProduct;

    private Integer productQuantity;

    private String shippingAddress;

    private String paymentMethod;

    private Float total;
}
