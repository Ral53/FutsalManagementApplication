package com.example.futsalmanagement.service.impl;

import com.example.futsalmanagement.entity.Role;
import com.example.futsalmanagement.pojo.RolePojo;
import com.example.futsalmanagement.repository.RoleRepository;
import com.example.futsalmanagement.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getRoleById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public RolePojo createRole(RolePojo rolePojo) {
        Role role = convertToEntity(rolePojo);
        Role savedRole = roleRepository.save(role);
        return convertToPojo(savedRole);
    }

    @Override
    public boolean deleteRoleById(Integer id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Helper method to convert Role entity to RolePojo
    private RolePojo convertToPojo(Role role) {
        RolePojo rolePojo = new RolePojo();
        BeanUtils.copyProperties(role, rolePojo);
        return rolePojo;
    }

    // Helper method to convert RolePojo to Role entity
    private Role convertToEntity(RolePojo rolePojo) {
        Role role = new Role();
        BeanUtils.copyProperties(rolePojo, role);
        return role;
    }
}
