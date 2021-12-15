package asd.amazon.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "BATCH")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Batch implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "PRICE", nullable = false)
	private Float price;

	@Column(name = "AVAILABLE_QUANTITY", nullable = false)
	private Float availableQuantity;

	@OneToOne(mappedBy = "batch")
	private Product product;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Batch batch = (Batch) o;
		return id != null && Objects.equals(id, batch.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
