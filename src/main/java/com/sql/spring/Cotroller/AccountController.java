package com.sql.spring.Cotroller;


import com.sql.spring.Models.Account;
import com.sql.spring.Models.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountDAO accountDao;

    @GetMapping("/account")
    @ResponseBody
    public
    List <Account> getAccounts() {
        return (List<Account>) accountDao.findAll();
    }

    @GetMapping("/account/{id}")
    @ResponseBody
    public
    Account getAccountById( @PathVariable int id){
        return accountDao.findById(id);
    }

    @PostMapping("/save")
    public
    ResponseEntity<Object> createAccount( @RequestBody Account account) {
        Account savedAccount =  accountDao.save(account);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAccount.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/account/{id}")
    public void modifyAccountById(@PathVariable("id") int id, @Valid @RequestBody Account account) {
        account.setId(id);
        accountDao.save(account);
    }


    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable int id) {
        accountDao.delete(accountDao.findById(id));
    }
}

