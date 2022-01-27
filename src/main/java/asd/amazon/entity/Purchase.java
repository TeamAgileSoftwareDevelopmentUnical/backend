package asd.amazon.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "PURCHASE")
public class Purchase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    /*@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_ID", referencedColumnName = "ID")
    private Product product;*/

    /*@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
    private CustomerAccount customer;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
    private CustomerAccount customer;

    @Column(name = "DATE", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime date;

    @Column(name = "PRODUCT_QUANTITY", nullable = false)
    private Integer productQuantity;

    @Column(name = "SHIPPING_ADDRESS", nullable = false)
    private String shippingAddress;

    @Column(name = "PAYMENT_METHOD", nullable = false)
    private String paymentMethod;
}
