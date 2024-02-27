package com.pblgllgs.todobackend.repository;
/*
 *
 * @author pblgl
 * Created on 27-02-2024
 *
 */

import com.pblgllgs.todobackend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
