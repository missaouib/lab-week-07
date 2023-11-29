package com.minh.labweek07.backend.pk;

import com.minh.labweek07.backend.models.User;
import com.minh.labweek07.backend.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class GrantPK implements Serializable {
    private Role role;
    private User account;
}
