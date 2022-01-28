package asd.amazon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asd.amazon.entity.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch,Long> {

    Batch findBatchByProduct(Long productId);

}
