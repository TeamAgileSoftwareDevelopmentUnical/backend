package asd.amazon.repository;

import asd.amazon.entity.Product;
import asd.amazon.entity.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query("SELECT p FROM Product as p, Batch as b WHERE p.type=:category and p.id=b.product.id order by b.price")
    List<Product> findAllByType(Type category);
}
