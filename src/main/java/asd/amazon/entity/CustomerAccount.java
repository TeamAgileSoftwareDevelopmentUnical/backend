package asd.amazon.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerAccount extends Account{

    @Column(name = "SHIPPING_ADDRESS", nullable = true)
    private String shippingAddress;

    /*@OneToOne(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Purchase purchase;*/
    @OneToMany(mappedBy = "customer")
    private List<Purchase> purchases;
}
