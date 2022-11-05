package com.webapp.doan.controller;

import com.webapp.doan.model.Account;
import com.webapp.doan.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class AdminController {
    @Autowired
    AccountService accountService;

    @GetMapping("")
    public ResponseEntity<Map<String, List<Account>>> getAllUsers() {
        List<Account> users = accountService.findAllUser();
        Map<String, List<Account>> map = new HashMap<>();
        map.put("data", users);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Map<String, Account>> findUserById(@PathVariable("username") String username) {
        Account user = accountService.findUserByName(username);
        Map<String, Account> map = new HashMap<>();
        map.put("data", user);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
