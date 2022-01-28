package asd.amazon.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "SELLER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SellerAccount extends Account{

    @OneToMany(mappedBy = "sellerAccounts")
    private List<Product> products;

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
