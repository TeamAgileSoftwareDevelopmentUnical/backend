package asd.amazon.repository;

import asd.amazon.entity.Product;
import asd.amazon.entity.Purchase;
import asd.amazon.entity.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long>, JpaSpecificationExecutor<Purchase> {

    List<Purchase> findAllByCustomerIdOrderByDateDesc(Long customer);
}