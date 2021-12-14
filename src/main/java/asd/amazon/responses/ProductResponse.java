package asd.amazon.responses;

import asd.amazon.entity.Product;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponse {
    private Long productId;

    private String productName;

    private String productDesc;

    private Product.Type type;

    private BatchResponse batch;

    private SellerResponse seller;
}
