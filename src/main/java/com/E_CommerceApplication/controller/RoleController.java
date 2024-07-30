//package com.E_CommerceApplication.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.E_CommerceApplication.entity.Role;
//import com.E_CommerceApplication.service.RoleService;
//
//@RestController
//@RequestMapping("/api/v1/roles")
//public class RoleController {
//
//    @Autowired
//    private RoleService roleService;
//
//    @PostMapping
//    public ResponseEntity<Role> createRole(@RequestBody Role role, @RequestParam Integer id) {
//        Role createdRole = roleService.createRole(role, id);
//        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Role> getRole(@PathVariable Integer id) {
//        Role role = roleService.getRole(id);
//        return new ResponseEntity<>(role, HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Role> updateRole(@PathVariable Integer id, @RequestBody Role role) {
//        Role updatedRole = roleService.updateRole(id, role);
//        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
//        roleService.deleteRole(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Role>> getAllRoles() {
//        List<Role> roles = roleService.getAllRole(null);
//        return new ResponseEntity<>(roles, HttpStatus.OK);
//    }
//}
