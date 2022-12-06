package com.webapp.doan.controller;

import com.webapp.doan.dto.LoginDto;
import com.webapp.doan.model.Account;
import com.webapp.doan.service.AccountService;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AccountController {
    @Autowired
    AccountService accountService;
    @GetMapping("")
    public String homepage() {
        return "Hello world from Spring boot";
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody LoginDto loginDto) {
        Account acc = accountService.loginUser(loginDto.getUsername(), loginDto.getPassword());
        return new ResponseEntity<>(generateJWTToken(acc), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Account>> RegisterAccount(Account payload) {
        System.out.print(payload);
        Map<String, Account> map = new HashMap<>();
        Account account = accountService.registerUser(payload);
        map.put("data", account);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    private Map<String, String> generateJWTToken(Account account) {
        Dotenv dotenv = Dotenv.configure().load();
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, dotenv.get("API_SECRET_KEY"))
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(System.currentTimeMillis() + Integer.parseInt(dotenv.get("TOKEN_VALIDITY"))))
                .claim("id", account.getId())
                .claim("username", account.getUsername())
                .claim("password", account.getPassword())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("id", account.getId().toString());
        map.put("username", account.getUsername());
        return map;
    }
}
