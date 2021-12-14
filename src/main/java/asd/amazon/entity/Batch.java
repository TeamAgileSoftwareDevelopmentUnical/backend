package asd.amazon.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "BATCH")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Batch implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	// @EmbeddedId
	@OneToOne(mappedBy = "batch")
	private Product product;

	// @EmbeddedId
	@OneToOne
	@JoinColumn(name = "SELLER_ID",referencedColumnName = "ID")
	private SellerAccount seller;

	@Column(name = "PRICE", nullable = false)
	private Float price;

	@Column(name = "AVAILABLE_QUANTITY", nullable = false)
	private Float availableQuantity;

}
