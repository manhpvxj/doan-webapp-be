package com.webapp.doan.service;

import com.webapp.doan.exceptions.EtAuthException;
import com.webapp.doan.model.Account;
import com.webapp.doan.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Override
    public Account loginUser(String username, String password) {
        try {
            Account acc = accountRepository.findByUsername(username);

            if (acc == null) {
                throw new EtAuthException("Invalid username");
            }

            if (!BCrypt.checkpw(password, acc.getPassword())) {
                throw new EtAuthException("Invalid password");
            }
            return acc;
        }
        catch (EmptyResultDataAccessException e) {
            throw new EtAuthException("Invalid username or password");
        }
    }

    @Override
    public Account registerUser(Account account) {
        Account acc = accountRepository.findByUsername(account.getUsername());
        if(acc != null) {
            throw new EtAuthException("Username is already use");
        }
        String hashedPassword = BCrypt.hashpw(account.getPassword(), BCrypt.gensalt(10));
        account.setPassword(hashedPassword);
        Account newAcc = accountRepository.save(account);
        return newAcc;
    }

    @Override
    public List<Account> findAllUser() {
        return accountRepository.findAll();
    }

    @Override
    public Account findUserByName(String name) {
        return accountRepository.findByUsername(name);
    }
}
