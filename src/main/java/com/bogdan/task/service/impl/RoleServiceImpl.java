package com.bogdan.task.service.impl;

import com.bogdan.task.entity.Role;
import com.bogdan.task.repository.RoleRepository;
import com.bogdan.task.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAllRoles(){
        return roleRepository.findAll();
    }
}
