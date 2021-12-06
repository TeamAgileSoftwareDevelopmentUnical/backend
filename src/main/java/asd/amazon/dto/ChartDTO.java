package asd.amazon.dto;

import asd.amazon.entity.CustomerAccount;
import asd.amazon.entity.Product;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class ChartDTO {

    private Long id;

    private CustomerAccount customer;

    private LocalDateTime date;

    private List<ProductDTO> soldProducts;

    private String shippingAddress;

    private String paymentMethod;

    private Float total;
}
