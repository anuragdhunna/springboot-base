package com.springboot.base.controllers;

import com.springboot.base.models.Role;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author anuragdhunna
 */
@RestController
@RequestMapping(value = "/api")
public class RoleController extends BaseController {

    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    @ResponseBody
    public Role addRole(@RequestBody Role role){
        return roleService.saveRole(role);
    }

    @RequestMapping(value = "/getAllRoles",method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getAllRoles(){
        return roleService.listAllRoles();
    }

    @RequestMapping(value = "/getRoleById",method = RequestMethod.GET)
    @ResponseBody
    public Role getRoleById(@RequestParam Long id){
        return roleService.findById(id);
    }
}
