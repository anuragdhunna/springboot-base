package com.springboot.base.services;

import com.springboot.base.models.Role;

import java.util.List;

/**
 * @author anuragdhunna
 */
public interface RoleService {

    Role saveRole(Role role);

    List<Role> listAllRoles();

    Role findById(Long id);

    Role roleName(String roleName);

    void createDefaultRoles(Role role);
}
