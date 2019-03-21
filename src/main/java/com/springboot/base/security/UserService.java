package com.springboot.base.security;

import com.springboot.base.models.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> showAllUsers();

    User getUser(String username);

    void deleteUser(Long id);

    User findById(Long id);

}
