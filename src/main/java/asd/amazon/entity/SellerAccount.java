package asd.amazon.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SELLER")
@Embeddable
public class SellerAccount extends Account{

    @OneToOne
    private Batch batch;

    @Column(name = "PAYMENT_ADDRESS", nullable = false)
    private String paymentAddress;
}
