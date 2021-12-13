package asd.amazon.repository;

import asd.amazon.entity.Batch;
import asd.amazon.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch,Long> {
    Batch findByProduct(Product product);
}
