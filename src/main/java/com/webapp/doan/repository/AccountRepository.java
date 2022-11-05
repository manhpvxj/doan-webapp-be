package com.webapp.doan.repository;

import com.webapp.doan.exceptions.EtAuthException;
import com.webapp.doan.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    public Account findByUsername(String username) throws EtAuthException;
}
