package asd.amazon.dto;

import lombok.Data;

import java.util.List;

@Data
public class SellerAccountDTO  extends AccountDTO{

    private List<ProductDTO> products;

    private String paymentAddress;
}
