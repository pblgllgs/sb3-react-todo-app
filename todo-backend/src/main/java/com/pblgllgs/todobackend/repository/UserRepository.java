package com.pblgllgs.todobackend.repository;
/*
 *
 * @author pblgl
 * Created on 27-02-2024
 *
 */

import com.pblgllgs.todobackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Boolean existsByEmail(String email);
}