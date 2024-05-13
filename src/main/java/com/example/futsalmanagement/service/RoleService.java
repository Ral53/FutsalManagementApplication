package com.example.futsalmanagement.service;

import com.example.futsalmanagement.entity.Role;
import com.example.futsalmanagement.pojo.RolePojo;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAllRoles();

    Optional<Role> getRoleById(Integer id);

    RolePojo createRole(RolePojo rolePojo);

    boolean deleteRoleById(Integer id);
}
