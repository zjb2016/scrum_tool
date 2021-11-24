package com.techtter.blog.scrum.controller;

import com.techtter.blog.scrum.model.User;
import com.techtter.blog.scrum.model.ScrumUserRole;
import com.techtter.blog.scrum.model.ScrumUserRoleDTO;
import com.techtter.blog.scrum.service.ScumUserRoleService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @program: scrum-board
 * @description: 用户登录注册
 * @author: zhangjb14
 * @create: 2021-11-11 09:42
 **/
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://www.test.com"})
public class UserRoleController {

    private final ScumUserRoleService scrumUserRoleService;

    @PostMapping("/add")
    @ApiOperation(value="add new role", response = User.class)
    public ResponseEntity<?> roleAdd(@RequestBody ScrumUserRoleDTO role){
        try {
            return new ResponseEntity<>(
                    scrumUserRoleService.saveNewScrumUserRole(role),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @PostMapping("/verify")
    @ApiOperation(value="verify Permission")
    public ResponseEntity<?> verifyPermission(@RequestParam String permission, @RequestParam Long id){
        try {
            Optional<ScrumUserRole> role = scrumUserRoleService.getScrumUserRoleById(id);
            if(role.isPresent()){
                return new ResponseEntity<>(
                        scrumUserRoleService.verifyRolePermission(permission,role.get()),
                        HttpStatus.OK);
            }
        } catch (Exception e) {
            return noRoleFoundResponse(id);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="delete role", response = String.class)
    public ResponseEntity<?> roleDel(@PathVariable Long id){
        try {
            Optional<ScrumUserRole> optRole = scrumUserRoleService.getScrumUserRoleById(id);
            if (optRole.isPresent()) {
                scrumUserRoleService.deleteScrumUserRole(optRole.get());
                return new ResponseEntity<>(String.format("role with id: %d was deleted", id), HttpStatus.OK);
            } else {
                return noRoleFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    private ResponseEntity<?> noRoleFoundResponse(Long id) {
        return new ResponseEntity<>("no role found with id: "+ id, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<?> errorResponse() {
        return new ResponseEntity<>("Something went wrong :(", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
