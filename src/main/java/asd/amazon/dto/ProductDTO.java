package asd.amazon.dto;

import asd.amazon.entity.enums.Type;
import lombok.Data;

@Data
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private Float price;

    private Long sellerID;

    private Float availableQuantity;

    private Type type;

    private String photo;
}
