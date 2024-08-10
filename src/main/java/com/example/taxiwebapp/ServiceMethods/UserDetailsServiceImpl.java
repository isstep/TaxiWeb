package com.example.taxiwebapp.ServiceMethods;

import com.example.taxiwebapp.EntityClasses.RolesEntity;
import com.example.taxiwebapp.EntityClasses.UsersEntity;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final RoleService roleService;

    public UserDetailsServiceImpl(UserService userService, RoleService roleService, HttpServletResponse httpServletResponse) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (user.isBlocked()) {
            throw new LockedException("User is blocked");
        }

        RolesEntity role = roleService.findByRoleId(user.getRoleId());

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getUserPassword())
                .roles(new String[]{role.getRoleName()})
                .build();
    }
}

