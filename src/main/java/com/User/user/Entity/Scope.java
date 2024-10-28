package com.User.user.Entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Scope {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long scope_id;
    @Column(name = "scope_name")
    public String scope_name;
    @OneToMany(mappedBy ="scope",fetch = FetchType.EAGER)
    public Set<Permission> permissions;

}
