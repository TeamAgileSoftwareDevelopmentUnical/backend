package asd.amazon.dto;

import lombok.Data;

@Data
public class CustomerAccountDTO extends AccountDTO {

    private PurchaseDTO purchase;
}
