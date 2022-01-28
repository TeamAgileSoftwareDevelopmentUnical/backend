package asd.amazon.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SellerAccountDTO  extends AccountDTO{

    private List<ProductDTO> products;

    private String paymentAddress;
}
