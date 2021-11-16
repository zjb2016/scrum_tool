package com.techtter.blog.scrum.service;

import com.techtter.blog.scrum.model.*;

import java.util.List;
import java.util.Optional;

public interface ScrumUserService {
    // add new user
    ScrumUser saveNewScrumUser(ScrumUserDTO scrumUserDTO);

    // delete user
    void deleteScrumUser(ScrumUser scrumUser) ;

    // query user
    List<ScrumUser> getAllScrumUsers();

    Optional<ScrumUser> getScrumUserById(Long id);

    Optional<ScrumUser> getScrumUserByPhone(String phone);

    // update user
    ScrumUser updateScrumUser(ScrumUser oldScrumUser, ScrumUserDTO newScrumUserDTO);

    // verify user login
    Boolean verifyUser(ScrumUserDTO queryUser);

}
