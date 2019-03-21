package com.springboot.base.controllers;

import com.springboot.base.repositories.UserRepository;
import com.springboot.base.security.UserService;
import com.springboot.base.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anuragdhunna
 */
@RestController
abstract class BaseController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bcryptEncoder;
}