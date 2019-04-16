package com.bogdan.myspringtask.repository;

import com.bogdan.myspringtask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String name);
}
