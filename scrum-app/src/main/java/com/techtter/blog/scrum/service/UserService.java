package com.techtter.blog.scrum.service;

import com.techtter.blog.scrum.model.*;

import java.util.List;
import java.util.Optional;

public interface UserService {
    // add new user
    User saveNewScrumUser(UserDTO UserDTO);

    // delete user
    void deleteScrumUser(User User) ;

    // query user
    List<User> getAllScrumUsers();

    Optional<User> getScrumUserById(Integer id);

    Optional<User> getScrumUserByPhone(String phone);

    // update user
    User updateScrumUser(User oldScrumUser, UserDTO newScrumUserDTO);

    // verify user login
    User verifyUser(UserDTO queryUser);

}
