package com.User.user.Repository;

import com.User.user.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail1);
    boolean existsByEmail(String Email);
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
