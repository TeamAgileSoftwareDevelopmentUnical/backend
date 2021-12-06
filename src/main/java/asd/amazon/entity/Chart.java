package asd.amazon.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "CHART")
public class Chart implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CUSTOMER_ID", referencedColumnName = "ID")
    private CustomerAccount customer;

    @Column(name = "DATE", nullable = false, columnDefinition = "TIMESTAMP")
    private Date date;

    //an order has a list of products and a product can refers or not to just one order?
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PURCHASE",
            joinColumns = {@JoinColumn(name = "CHART_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")}
    )
    private List<Product> soldProducts;

    @Column(name = "SHIPPING_ADDRESS", nullable = false)
    private String shippingAddress;

    @Column(name = "PAYMENT_METHOD", nullable = false)
    private String paymentMethod;

    @Column(name = "TOTAL", nullable = false)
    private Float total;

    //total price as an aggregate attribute (sum all product.price)
}
