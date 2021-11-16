package com.techtter.blog.scrum.service;

import com.techtter.blog.scrum.model.ScrumUserRole;
import com.techtter.blog.scrum.model.ScrumUserRoleDTO;

import java.util.List;
import java.util.Optional;

/**
 * @program: scrum-board
 * @description:
 * @author: zhangjb14
 * @create: 2021-11-15 20:53
 **/
public interface ScumUserRoleService {
    // add new role
    ScrumUserRole saveNewScrumUserRole(ScrumUserRoleDTO scrumUserRoleDTO);

    // delete role
    void deleteScrumUserRole(ScrumUserRole scrumUserRole) ;

    // query role
    List<ScrumUserRole> getAllScrumUserRoles();

    // verify role permission
    Boolean verifyRolePermission(String permissionTag, ScrumUserRole role);


    Optional<ScrumUserRole> getScrumUserRoleById(Long id);
}
