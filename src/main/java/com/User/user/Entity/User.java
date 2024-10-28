package com.User.user.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "User_details")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long user_id;
    @Column(name = "Username",unique = true)
    public String user_name;
    @Column(name = "First name")
    public String first_name;
    @Column(name="Middle name")
    public String middle_name;
    public String password;
    public String email;
    @Column(name = "Contact")
    private String contact;
    @Column(name = "Expiration date")
    public LocalDateTime date;
    @Column(name="Description")
    public String description;
    @Column(name = "Timeout")
    public LocalDateTime timeout;
    @Column(name = "Datetime format")
    public LocalDateTime date_time_format;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

}
