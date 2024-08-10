package com.example.taxiwebapp.ServiceMethods;

import com.example.taxiwebapp.EntityClasses.RolesEntity;
import com.example.taxiwebapp.EntityRepositories.RolesRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RolesRepository roleRepository;

    public RoleService(RolesRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RolesEntity findByRoleId(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
}