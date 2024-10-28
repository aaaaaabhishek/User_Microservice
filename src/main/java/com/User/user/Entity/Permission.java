package com.User.user.Entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long permission_id;
    private String permission_name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "scope_id")
    private Scope scope;
    @Column(name = "Primary group name")
    private String primary_group_name;
    @Column(name = "Secondary group name")
    public List<String> secondary_group;
    @Column(name = "Language")
    public String language_name;
    @Column(name = "Organization")
    public String organization;
    @Column(name="Time_Zone")
    public String TimeZone;
    @Column(name="Memo")
    public String memo;

}
