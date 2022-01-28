package asd.amazon.responses;

import asd.amazon.entity.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponse {
    private Long productId;

    private String productName;

    private String productDesc;

    private Type type;

    private String photo;

    private BatchResponse batch;

    private SellerResponse seller;
}
