package asd.amazon.repository;

import asd.amazon.entity.SellerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerAccountRepository  extends JpaRepository<SellerAccount, Long>, JpaSpecificationExecutor<SellerAccount> {
}
