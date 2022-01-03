package asd.amazon.repository;

import asd.amazon.entity.Account;
import asd.amazon.entity.CustomerAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long>, JpaSpecificationExecutor<CustomerAccount> {

    CustomerAccount findCustomerAccountsById(Long id);

    CustomerAccount findByUsername(@Param(value = "username") String username);

    @Query("SELECT a FROM Account a WHERE :username=a.username AND :password=a.password")
    Account checkPassword(@Param(value = "password") String password, @Param(value = "username") String username);
    
    @Query("UPDATE Account a SET a.active = false WHERE :username=a.username")
    void deactivateUser(@Param(value = "username") String username);

    @Query("UPDATE Account a SET a.active = true WHERE :username=a.username")
    void activateUser(@Param(value = "username") String username);

    @Query("SELECT acc FROM Account acc WHERE acc.username = :username AND acc.password = :password")
    Account findByUsernameAndPassword(@Param(value ="username") String username, @Param(value = "password") String password);
}
