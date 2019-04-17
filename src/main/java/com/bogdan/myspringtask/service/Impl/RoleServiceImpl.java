package com.bogdan.myspringtask.service.Impl;

import com.bogdan.myspringtask.entity.Role;
import com.bogdan.myspringtask.repository.RoleRepository;
import com.bogdan.myspringtask.service.RoleService;
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
