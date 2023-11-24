package com.minh.labweek07.backend.repository;

import com.minh.labweek07.backend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}