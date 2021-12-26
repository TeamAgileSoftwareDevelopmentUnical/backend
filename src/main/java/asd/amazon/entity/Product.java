package asd.amazon.entity;

import asd.amazon.entity.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    //description
    @Column(name = "DESCRIPTION", nullable = true)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = true)
    private Type type;

    @Lob
    @Column(name = "PHOTO", nullable = true)
    private byte[] photo;

    @OneToOne(mappedBy = "product")
    private Purchase purchase;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BATCH_ID", referencedColumnName = "ID")
    private Batch batch;

    @ManyToOne
    @JoinColumn(name = "SELLER_ID", referencedColumnName = "ID")
    private SellerAccount sellerAccounts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
