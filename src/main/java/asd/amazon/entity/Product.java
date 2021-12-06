package asd.amazon.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = true)
    private Type type;



    @OneToOne
    private Purchase purchase;

    @OneToOne
    private Batch batch;
    
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
