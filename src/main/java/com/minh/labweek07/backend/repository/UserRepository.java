package com.minh.labweek07.backend.repository;

import com.minh.labweek07.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findAccountByEmailAndPassword(String email, String password);
    public User findByUsername(String username);

    boolean existsByUsername(String username);
}