package asd.amazon.responses;

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
public class ViewDetailsResponse {

    private Long productId;

    private String productName;

    private String productDesc;

    private String photo;

    private Float productQuantity;


}
