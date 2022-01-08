package asd.amazon.entity;

import asd.amazon.entity.enums.PaymentProvider;
import asd.amazon.entity.enums.PaymentStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PAYMENT")
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long id;

    @Column(name = "PAYMENT_AMOUNT")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_PROVIDER")
    private PaymentProvider provider;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_STATUS")
    private PaymentStatus status;

    @Column(name = "PAYMENT_DATE")
    private LocalDate paymentDate;
}
