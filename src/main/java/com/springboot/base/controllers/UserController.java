package com.springboot.base.controllers;

import com.springboot.base.exceptionHandler.ResourceAlreadyExists;
import com.springboot.base.models.Role;
import com.springboot.base.models.User;
import com.springboot.base.security.UserService;
import com.springboot.base.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author anuragdhunna
 */
@RestController
@RequestMapping(path="api/user")
public class UserController extends BaseController{

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public List listUser(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "id") Long id){
        return userRepository.getOne(id);
    }

    /**
     * User Signup
     * @param user
     * @return
     */
    @RequestMapping(value="/signup",method = RequestMethod.POST)
    @ResponseBody
    public User saveUser (@RequestBody User user) throws ResourceAlreadyExists {
        User userExist = userService.getUser(user.getEmail());
        if(userExist != null){
            throw new ResourceAlreadyExists("User already exists");
        }
        Role role = roleService.roleName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        user.setRoles(roleSet);
        return userService.saveUser(user);
    }
}
