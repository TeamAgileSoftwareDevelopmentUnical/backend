package asd.amazon.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CUSTOMER")
public class CustomerAccount extends Account{

    @Column(name = "SHIPPING_ADDRESS", nullable = true)
    private String shippingAddress;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private Purchase purchase;
}
