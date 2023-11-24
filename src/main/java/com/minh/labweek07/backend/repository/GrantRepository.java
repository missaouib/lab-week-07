package com.minh.labweek07.backend.repository;

import com.minh.labweek07.backend.models.Grant;
import com.minh.labweek07.backend.models.Role;
import com.minh.labweek07.backend.pk.GrantPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrantRepository extends JpaRepository<Grant, GrantPK> {
    public Grant findGrantByRoleAndAccount_AccountID(Role role, String account);
}