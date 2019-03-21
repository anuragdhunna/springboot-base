package com.springboot.base.utils;

import com.springboot.base.models.Role;
import com.springboot.base.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author anuragdhunna
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    private RoleService roleService;

    public DataLoader(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading data...--------------------------------------------------------------::::::::::::::::::::::::::::");

        roleService.createDefaultRoles(new Role("USER", "User Role"));
        roleService.createDefaultRoles(new Role("ADMIN", "Admin Role"));
        roleService.createDefaultRoles(new Role("SUPER_ADMIN", "Super Admin Role"));
    }
}
