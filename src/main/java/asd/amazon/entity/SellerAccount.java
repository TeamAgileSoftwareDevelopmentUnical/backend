package asd.amazon.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "SELLER")
public class SellerAccount extends Account{

    //A seller has a list of products and a product refers to just one Seller?
    //Maybe yes because a Seller can add product with different quantity and price from the other Sellers
    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private List<Product> products;

    @Column(name = "PAYMENT_ADDRESS", nullable = false)
    private String paymentAddress;
}
