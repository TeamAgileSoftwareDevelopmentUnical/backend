package asd.amazon.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CUSTOMER")
public class CustomerAccount extends Account{

    @OneToOne(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private Purchase purchase;
}
