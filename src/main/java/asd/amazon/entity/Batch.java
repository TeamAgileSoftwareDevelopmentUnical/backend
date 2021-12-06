package asd.amazon.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "BATCH")
public class Batch implements Serializable{

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	// @EmbeddedId
	@OneToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;

	// @EmbeddedId
	@OneToOne
	@JoinColumn(name = "SELLER_ID")
	private SellerAccount seller;

	@Column(name = "PRICE", nullable = false)
	private Float price;

	@Column(name = "AVAILABLE_QUANTITY", nullable = false)
	private Float availableQuantity;

}
