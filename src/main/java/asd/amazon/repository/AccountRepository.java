package asd.amazon.repository;

import asd.amazon.entity.Account;
import asd.amazon.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

    Account findByUsernameAndActiveTrue(String username);

    Account findByEmailAndActiveTrue(String email);
}
