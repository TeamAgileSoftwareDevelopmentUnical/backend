package asd.amazon.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    //description
    @Column(name = "DESCRIPTION", nullable = true)
    private String description;

    @Column(name = "PRICE", nullable = false)
    private Float price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SELLER_ID", referencedColumnName = "ID")
    private SellerAccount seller;

    @Column(name="AVAILABLE_QUANTITY", nullable = false)
    private Float availableQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = true) //TODO: add nullable = false
    private Type type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PURHCASE_ID", referencedColumnName = "ID")
    private Purchase purchase;

    //CATEGORY = ENUM?
    public enum Type{
        VEGETABLE,
        MEAT,
        CEREAL
    }

    //addition date? (to identify when a product was added by a seller)
//    @Column(name = "ADDITION_DATE",)
//    private LocalDateTime additionDate;
}
