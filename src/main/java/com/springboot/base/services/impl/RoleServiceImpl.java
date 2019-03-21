package com.springboot.base.services.impl;

import com.springboot.base.models.Role;
import com.springboot.base.repositories.RoleRepository;
import com.springboot.base.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        logger.info("role======="+role.getDescription());


        return roleRepository.save(role);
    }

    @Override
    public List<Role> listAllRoles() {
        List<Role>roles=new ArrayList<Role>();
        for (Role role:roleRepository.findAll()){
            roles.add(role);
        }
        return roles;
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.getOne(id);
    }

    @Override
    public Role roleName(String roleName) {
        return roleRepository.getRoleByName(roleName);
    }

    @Override
    public void createDefaultRoles(Role role) {
        if (roleRepository.getRoleByName(role.getRoleName()) == null) {
            saveRole(role);
        }
    }
}
