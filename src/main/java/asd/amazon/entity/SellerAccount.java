package asd.amazon.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "SELLER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SellerAccount extends Account{

    @OneToMany(mappedBy = "sellerAccounts")
    private List<Product> product;

    @Column(name = "PAYMENT_ADDRESS", nullable = true)
    private String paymentAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SellerAccount that = (SellerAccount) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
