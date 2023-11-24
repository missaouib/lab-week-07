package com.minh.labweek07.backend.repository;

import com.minh.labweek07.backend.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    public Account findAccountByEmailAndPassword(String email, String password);
}