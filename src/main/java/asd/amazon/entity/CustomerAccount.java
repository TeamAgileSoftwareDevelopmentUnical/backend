package asd.amazon.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "CUSTOMER")
public class CustomerAccount extends Account{

    //a customer account has a list of Orders (history of all its orders?)
    //One Purchase refers to One Customer, but a Customer can store more Orders
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
       private List<Purchase> purchases;
    //TODO: if an account is removed, what happens to the orders made, when the user is deleted in CASCADE mode
}
