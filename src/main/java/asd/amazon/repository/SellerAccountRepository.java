package asd.amazon.repository;

import asd.amazon.entity.Account;
import asd.amazon.entity.SellerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerAccountRepository  extends JpaRepository<SellerAccount, Long>, JpaSpecificationExecutor<SellerAccount> {
    @Query("SELECT a FROM Account a WHERE :username=a.username AND :password=a.password")
    Account checkPassword(@Param(value = "password") String password, @Param(value = "username") String username);

    @Query("UPDATE Account a SET a.active = false WHERE :username=a.username")
    void deactivateUser(@Param(value = "username") String username);

    @Query("UPDATE Account a SET a.active = true WHERE :username=a.username")
    void activateUser(@Param(value = "username") String username);

    SellerAccount findSellerAccountById(Long id);
}
