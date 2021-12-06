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

    private List<ChartDTO> charts;

    private Product.Type type;
}
