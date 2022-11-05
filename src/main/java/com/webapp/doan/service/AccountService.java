package com.webapp.doan.service;

import com.webapp.doan.exceptions.EtAuthException;
import com.webapp.doan.exceptions.EtBadRequestException;
import com.webapp.doan.model.Account;

import java.util.List;

public interface AccountService {
    public Account loginUser(String username, String password) throws EtAuthException;
    public Account registerUser(Account account) throws EtAuthException;
    public List<Account> findAllUser() throws EtBadRequestException;
    public Account findUserByName(String name) throws EtBadRequestException;

}
