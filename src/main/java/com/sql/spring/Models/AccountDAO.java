package com.sql.spring.Models;

import org.springframework.data.repository.CrudRepository;

public
interface AccountDAO extends CrudRepository <Account, Long> {
    Iterable<Account> findAll ();

    Account findById ( int id );

}