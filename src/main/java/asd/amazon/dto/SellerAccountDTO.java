package asd.amazon.dto;

import lombok.Data;

import java.util.List;

@Data
public class SellerAccountDTO {

    private List<ProductDTO> products;

    private String paymentAddress;
}
