package asd.amazon.repository;

import asd.amazon.entity.Account;
import asd.amazon.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long>, JpaSpecificationExecutor<CustomerAccount> {

    CustomerAccount findByUsername(@Param(value = "username") String username);

    @Query("SELECT a FROM Account a WHERE a.username = :username AND a.password = :password")
    Account findByUsernameAndPassword(@Param(value = "username") String username, @Param(value = "password") String password);

}
