package com.User.user.Payload;

import com.User.user.Entity.Permission;
import com.User.user.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    public Long role_id;
    public String role_name;
    private Set<User> users;
    private Set<Permission> permissions;
}
