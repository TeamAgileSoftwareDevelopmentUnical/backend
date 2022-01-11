package asd.amazon.repository;

import asd.amazon.entity.Batch;
import asd.amazon.entity.Product;
import asd.amazon.entity.SellerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch,Long> {

    Batch findBatchByProduct(Long productId);

}
