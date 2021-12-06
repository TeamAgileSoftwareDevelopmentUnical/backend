package asd.amazon.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CUSTOMER")
public class CustomerAccount extends Account{

    @OneToOne(mappedBy = "customer")
    private Purchase purchase;
}
