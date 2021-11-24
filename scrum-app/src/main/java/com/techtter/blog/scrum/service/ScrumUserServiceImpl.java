package com.techtter.blog.scrum.service;

import com.techtter.blog.scrum.model.User;
import com.techtter.blog.scrum.model.UserDTO;
import com.techtter.blog.scrum.repository.ScrumUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScrumUserServiceImpl implements ScrumUserService {

    private final ScrumUserRepository scrumUserRepository;


    @Override
    public User saveNewScrumUser(UserDTO UserDTO) {
        log.info("save new user");
        return scrumUserRepository.save(convertDTOToScrumUser(UserDTO));
    }

    @Override
    public void deleteScrumUser(User User) {
        scrumUserRepository.delete(User);
    }

    @Override
    public List<User> getAllScrumUsers() {
        List<User> scrumUserList = new ArrayList<>();
        scrumUserRepository.findAll().forEach(scrumUserList::add);
        return scrumUserList;
    }

    @Override
    public Optional<User> getScrumUserById(Long id) {
        return scrumUserRepository.findById(id);
    }

    @Override
    public Optional<User> getScrumUserByPhone(String phone) {
        return scrumUserRepository.findByPhone(phone);
    }

    @Override
    public User updateScrumUser(User oldScrumUser, UserDTO newScrumUserDTO) {
        oldScrumUser.setStatus(newScrumUserDTO.getStatus());
        oldScrumUser.setPhone(newScrumUserDTO.getPhone());
        oldScrumUser.setUserName(newScrumUserDTO.getUserName());
        oldScrumUser.setUserPassword(newScrumUserDTO.getUserPassword());
        oldScrumUser.setRoleId(newScrumUserDTO.getRoleId());
        return scrumUserRepository.save(oldScrumUser);
    }

    @Override
    public User verifyUser(UserDTO queryUser) {
        User user;
        String queryPhone = queryUser.getPhone();
        Optional<User> userobj = scrumUserRepository.findByPhone(queryPhone);
        // 找不到直接抛出，返回http not found
        Assert.isTrue(userobj.isPresent(),"can not find user!");

        user = userobj.get();
        // 密码错误抛出
        Assert.state(user.getUserPassword().equals(queryUser.getUserPassword()), "error password");
        user.setUserPassword("********");
        return user;
    }

    private User convertDTOToScrumUser(UserDTO UserDTO){
        User user = new User();
        user.setPhone(UserDTO.getPhone());
        user.setUserName(UserDTO.getUserName());
        user.setUserPassword(UserDTO.getUserPassword());
        user.setRoleId(UserDTO.getRoleId());
        user.setStatus(UserDTO.getStatus());
        return user;
    }
}
