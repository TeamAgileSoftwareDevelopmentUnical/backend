package asd.amazon.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductUpdateRequest {
    private String productName;
    private String productDescription;
    private Float productPrice;
    private Float productQuantity;
}
