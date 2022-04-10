
package com.magazin.demo.controller;

import com.magazin.demo.model.Role;
import com.magazin.demo.model.User;
import com.magazin.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "Find all users", authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Users found!"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, users not found") })
    @GetMapping("/users")
    public ResponseEntity<List<User>>getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @ApiOperation(value = "Add new user", authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New user added to the database"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, new user could not be added") })
    @PostMapping("/user/save")
    public ResponseEntity<User>saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @ApiOperation(value = "Add a new role", authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New role added to the database"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, new role could not be added") })
    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @ApiOperation(value = "Add a new role", authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New role added  to the user"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, new role could not be added") })
    @PostMapping("/role/addtouser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Remove role from user", authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Role was removed from user"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, role could not be deleted") })
    @PostMapping("/role/delete")
    public ResponseEntity<?>removeRoleFromUser(@RequestBody RoleToUserForm form) {
        userService.removeRoleFromUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
    @ApiOperation(value = "Delete user", authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Deleted user from database"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, user could not be deleted") })
    @DeleteMapping(path="/delete/{username}")
    public ResponseEntity<String>deleteUser(@PathVariable String username) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        userService.deleteUser(username);
        return ResponseEntity.created(uri).body("User removed");
    }

}
@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}

