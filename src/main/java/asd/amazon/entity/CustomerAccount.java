package asd.amazon.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CUSTOMER")
@Embeddable
public class CustomerAccount extends Account{

    @OneToOne(mappedBy = "customer")
    private Purchase purchase;
}
