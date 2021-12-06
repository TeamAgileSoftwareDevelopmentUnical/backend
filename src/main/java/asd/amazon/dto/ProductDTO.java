package asd.amazon.dto;

import asd.amazon.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private Float price;

    private SellerAccountDTO seller;

    private Float availableQuantity;

    private Product.Type type;

    private List<PurchaseDTO> purchase;

    private BatchDTO batch;
}
