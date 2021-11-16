package com.techtter.blog.scrum.service;

import com.techtter.blog.scrum.model.ScrumUserRole;
import com.techtter.blog.scrum.model.ScrumUserRoleDTO;
import com.techtter.blog.scrum.repository.ScrumUserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @program: scrum-board
 * @description:
 * @author: zhangjb14
 * @create: 2021-11-15 20:57
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class ScumUserRoleServiceImpl implements ScumUserRoleService {
    private final ScrumUserRoleRepository scrumUserRoleRepository;

    @Override
    public ScrumUserRole saveNewScrumUserRole(ScrumUserRoleDTO scrumUserRoleDTO) {
        log.info("add new role");
        return scrumUserRoleRepository.save(convertDTOToScrumUserRole(scrumUserRoleDTO));
    }

    private ScrumUserRole convertDTOToScrumUserRole(ScrumUserRoleDTO scrumUserRoleDTO) {
        ScrumUserRole role = new ScrumUserRole();
        role.setRoleName(scrumUserRoleDTO.getRoleName());
        role.setRolePermission(scrumUserRoleDTO.getRolePermission());
        return role;
    }

    @Override
    public void deleteScrumUserRole(ScrumUserRole scrumUserRole) {
        scrumUserRoleRepository.deleteByRoleName(scrumUserRole.getRoleName());
    }

    @Override
    public List<ScrumUserRole> getAllScrumUserRoles() {
        List<ScrumUserRole> list = new ArrayList<>();
        scrumUserRoleRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Boolean verifyRolePermission(String permissionTag, ScrumUserRole scrumUserRoleDTO) {
        boolean hasTag = false;
        Optional<ScrumUserRole> role = scrumUserRoleRepository.findById(scrumUserRoleDTO.getId());
        if(role.isPresent()){
            hasTag = role.get().getRolePermission().contains(permissionTag);
            return hasTag;
        }
        return hasTag;
    }

    @Override
    public Optional<ScrumUserRole> getScrumUserRoleById(Long id) {
        return scrumUserRoleRepository.findById(id);
    }
}
