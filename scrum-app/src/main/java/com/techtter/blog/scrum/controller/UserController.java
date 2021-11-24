package com.techtter.blog.scrum.controller;

import com.alibaba.fastjson.JSONObject;
import com.techtter.blog.scrum.model.User;
import com.techtter.blog.scrum.model.UserDTO;
import com.techtter.blog.scrum.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
@CrossOrigin(origins = {"http://localhost:9000","http://www.test.com"})
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ApiOperation(value="register new user", response = User.class)
    public ResponseEntity<?> userRegister(@RequestBody UserDTO UserDTO){
        try {
            User newuser = userService.saveNewScrumUser(UserDTO);
            JSONObject obj = wrapUserJsonObj(newuser);
            obj.remove("token");
            obj.remove("failureTime");
            return new ResponseEntity<>(
                    obj,
                    HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @PostMapping("/login")
    @ApiOperation(value="login user")
    public ResponseEntity<?> userLogin(@RequestBody UserDTO UserDTO){
        try {
            User user = userService.verifyUser(UserDTO);
            JSONObject obj = wrapUserJsonObj(user);
            ResponseEntity<JSONObject> res = new ResponseEntity<>(obj, HttpStatus.OK);
            return res;
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND);
        }
    }

    private JSONObject wrapUserJsonObj(User user) {
        Date now =new Date();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", user.getId());
        jsonObject.put("userName", user.getUserName());
        jsonObject.put("phone", user.getPhone());
//        jsonObject.put("role", user.getRoleId());
        jsonObject.put("token", now.getTime());
        jsonObject.put("failureTime", now.getTime()/1000+24*3600);
        return  jsonObject;
    }

    @PutMapping("/{id}")
    @ApiOperation(value="modify user info", response = User.class)
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserDTO UserDTO){
        try {
            //find user by old id
            Optional<User> optUser = userService.getScrumUserById(id);
            if(optUser.isPresent()){
                return new ResponseEntity<>(userService.updateScrumUser(optUser.get(), UserDTO),
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
    public ResponseEntity<?> userDeleteo(@PathVariable int id){
        try {
            Optional<User> optUser = userService.getScrumUserById(id);
            if (optUser.isPresent()) {
                userService.deleteScrumUser(optUser.get());
                return new ResponseEntity<>(String.format("user with id: %d was deleted", id), HttpStatus.OK);
            } else {
                return noUserFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    private ResponseEntity<?> noUserFoundResponse(int id) {
        return new ResponseEntity<>("no user found with id: "+ id, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<?> errorResponse() {
        return new ResponseEntity<>("Something went wrong :(", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
