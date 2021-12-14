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

    private Long sellerID;

    private Float availableQuantity;

    private Product.Type type;
}
