package asd.amazon.repository;

import asd.amazon.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    Account findByUsernameAndActiveTrue(String username);

    Account findByEmailAndActiveTrue(String email);
}
