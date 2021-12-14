package asd.amazon.repository;

import asd.amazon.entity.SellerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerAccountRepository  extends JpaRepository<SellerAccount, Long>, JpaSpecificationExecutor<SellerAccount> {
    @Query("SELECT * FROM account WHERE :username=ACCOUNT_USERNAME AND :password=ACCOUNT_PASSWORD")
    Boolean checkPassword(@Param(value = "password") String password, @Param(value = "username") String username);
    
    @Query("UPDATE account SET ACTIVE = false WHERE :username=ACCOUT_USERNAME")
    void deactivateUser(@Param(value = "username") String username);

    @Query("UPDATE account SET ACTIVE = true WHERE :username=ACCOUT_USERNAME")
    void activateUser(@Param(value = "username") String username);
}
