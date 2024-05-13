package com.example.futsalmanagement.controller;

import com.example.futsalmanagement.entity.Role;
import com.example.futsalmanagement.pojo.RolePojo;
import com.example.futsalmanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok().body(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Integer id) {
        Optional<Role> role = roleService.getRoleById(id);
        return role.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<RolePojo> createRole(@RequestBody RolePojo rolePojo) {
        RolePojo createdRole = roleService.createRole(rolePojo);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRoleById(@PathVariable Integer id) {
        boolean deleted = roleService.deleteRoleById(id);
        if (deleted) {
            return ResponseEntity.ok("Role deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");
        }
    }

}
