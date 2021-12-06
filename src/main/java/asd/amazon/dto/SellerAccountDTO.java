package asd.amazon.dto;

import asd.amazon.entity.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SellerAccountDTO {

    private List<ProductDTO> products;

    private String paymentAddress;
}
