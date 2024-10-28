package com.User.user.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ScopeDto {
    private Long scopeId;            // Corresponds to scope_id
    private String scopeName;        // Corresponds to scope_name
    private Set<PermissionDTO> permissions; // Set
}
