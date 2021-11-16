package com.techtter.blog.scrum.service;

import com.techtter.blog.scrum.model.ScrumUser;
import com.techtter.blog.scrum.model.ScrumUserDTO;
import com.techtter.blog.scrum.repository.ScrumUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScrumUserServiceImpl implements ScrumUserService {

    private final ScrumUserRepository scrumUserRepository;


    @Override
    public ScrumUser saveNewScrumUser(ScrumUserDTO scrumUserDTO) {
        log.info("save new user");
        return scrumUserRepository.save(convertDTOToScrumUser(scrumUserDTO));
    }

    @Override
    public void deleteScrumUser(ScrumUser scrumUser) {
        scrumUserRepository.delete(scrumUser);
    }

    @Override
    public List<ScrumUser> getAllScrumUsers() {
        List<ScrumUser> scrumUserList = new ArrayList<>();
        scrumUserRepository.findAll().forEach(scrumUserList::add);
        return scrumUserList;
    }

    @Override
    public Optional<ScrumUser> getScrumUserById(Long id) {
        return scrumUserRepository.findById(id);
    }

    @Override
    public Optional<ScrumUser> getScrumUserByPhone(String phone) {
        return scrumUserRepository.findByPhone(phone);
    }

    @Override
    public ScrumUser updateScrumUser(ScrumUser oldScrumUser, ScrumUserDTO newScrumUserDTO) {
        oldScrumUser.setStatus(newScrumUserDTO.getStatus());
        oldScrumUser.setPhone(newScrumUserDTO.getPhone());
        oldScrumUser.setUserName(newScrumUserDTO.getUserName());
        oldScrumUser.setUserPassword(newScrumUserDTO.getUserPassword());
        oldScrumUser.setRoleId(newScrumUserDTO.getRoleId());
        return scrumUserRepository.save(oldScrumUser);
    }

    @Override
    public Boolean verifyUser(ScrumUserDTO queryUser) {
        String queryPhone = queryUser.getPhone();
        Optional<ScrumUser> user = scrumUserRepository.findByPhone(queryPhone);
        if(user.isPresent())
            return user.get().getUserPassword().equals(queryUser.getUserPassword())&&user.get().getUserName().equals(queryUser.getUserName());
        return false;
    }

    private ScrumUser convertDTOToScrumUser(ScrumUserDTO scrumUserDTO){
        ScrumUser user = new ScrumUser();
        user.setPhone(scrumUserDTO.getPhone());
        user.setUserName(scrumUserDTO.getUserName());
        user.setUserPassword(scrumUserDTO.getUserPassword());
        user.setRoleId(scrumUserDTO.getRoleId());
        user.setStatus(scrumUserDTO.getStatus());
        return user;
    }
}
