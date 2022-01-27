package asd.amazon.responses;

import asd.amazon.entity.enums.Type;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ViewDetailsResponse {

    private Long productId;

    private String productName;

    private String productDesc;

    private String photo;

    private Float productQuantity;


}
