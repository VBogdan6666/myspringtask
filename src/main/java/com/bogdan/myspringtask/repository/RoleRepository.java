package com.bogdan.myspringtask.repository;

import com.bogdan.myspringtask.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
