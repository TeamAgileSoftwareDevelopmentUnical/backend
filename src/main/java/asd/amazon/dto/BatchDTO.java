package asd.amazon.dto;

import java.util.List;

import lombok.Data;

@Data
public class BatchDTO {
	private Long id;
	private List<ProductDTO> products;
	private SellerAccountDTO seller;
	private Float price;
	private Float availableQuantity;
}
