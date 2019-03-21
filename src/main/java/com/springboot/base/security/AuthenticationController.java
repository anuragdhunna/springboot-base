package com.springboot.base.security;

import com.springboot.base.exceptionHandler.CustomException;
import com.springboot.base.exceptionHandler.ResourceNotFoundException;
import com.springboot.base.models.User;
import com.springboot.base.models.requestModels.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    /**
     * User Login API
     * @param loginUser
     * @return
     * @throws org.springframework.security.core.AuthenticationException
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public User login(@RequestBody LoginUser loginUser) throws AuthenticationException {

        User user = userService.getUser(loginUser.getUsername());
        if (user== null) {
            throw new ResourceNotFoundException("Invalid credentials. Please try again");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(loginUser.getPassword(), user.getPassword())) {
            throw new CustomException("Invalid credentials. Please try again");
        }
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);

        user.setToken(token);
        userService.saveUser(user);
        return user;
    }

}
