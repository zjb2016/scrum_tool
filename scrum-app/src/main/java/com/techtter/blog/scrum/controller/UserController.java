package com.techtter.blog.scrum.controller;

import com.techtter.blog.scrum.model.ScrumUser;
import com.techtter.blog.scrum.model.ScrumUserDTO;
import com.techtter.blog.scrum.service.ScrumUserService;
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
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://www.test.com"})
public class UserController {

    private final ScrumUserService scrumUserService;

    @PostMapping("/register")
    @ApiOperation(value="register new user", response = ScrumUser.class)
    public ResponseEntity<?> userRegister(@RequestBody ScrumUserDTO scrumUserDTO){
        try {
            return new ResponseEntity<>(
                    scrumUserService.saveNewScrumUser(scrumUserDTO),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @PostMapping("/login")
    @ApiOperation(value="login user")
    public ResponseEntity<?> userLogin(@RequestBody ScrumUserDTO scrumUserDTO){
        try {
            return new ResponseEntity<>(
                    scrumUserService.verifyUser(scrumUserDTO),
                    HttpStatus.OK);
        } catch (Exception e) {
            return noUserFoundResponse(scrumUserDTO.getId());
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value="modify user info", response = ScrumUser.class)
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody ScrumUserDTO scrumUserDTO){
        try {
            //find user by old id
            Optional<ScrumUser> optUser = scrumUserService.getScrumUserById(id);
            if(optUser.isPresent()){
                return new ResponseEntity<>(
                        scrumUserService.updateScrumUser(optUser.get(), scrumUserDTO),
                        HttpStatus.OK);
            }else {
                return noUserFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="delete user", response = String.class)
    public ResponseEntity<?> userDeleteo(@PathVariable Long id){
        try {
            Optional<ScrumUser> optUser = scrumUserService.getScrumUserById(id);
            if (optUser.isPresent()) {
                scrumUserService.deleteScrumUser(optUser.get());
                return new ResponseEntity<>(String.format("user with id: %d was deleted", id), HttpStatus.OK);
            } else {
                return noUserFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    private ResponseEntity<?> noUserFoundResponse(Long id) {
        return new ResponseEntity<>("no user found with id: "+ id, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<?> errorResponse() {
        return new ResponseEntity<>("Something went wrong :(", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
