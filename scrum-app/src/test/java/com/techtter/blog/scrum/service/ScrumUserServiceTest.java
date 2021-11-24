package com.techtter.blog.scrum.service;

import com.techtter.blog.scrum.model.User;
import com.techtter.blog.scrum.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScrumUserServiceTest {

    UserService UserService;
    @Mock
    UserRepository userRepository;

    @Before
    public void init() {
        UserService = new UserServiceImpl(userRepository);
    }

    @Test
    public void when2ScrumsInDatabase_thenGetListWithAllOfThem() {
        //given
        mockScrumInDatabase(2);

        //when
        List<User> users = UserService.getAllScrumUsers();

        //then
        assertEquals(2, users.size());
    }

    private void mockScrumInDatabase(int scrumCount) {
        when(userRepository.findAll())
                .thenReturn(createScrumUserList(scrumCount));
    }

    private Iterable<User> createScrumUserList(int scrumCount) {

        List<User> list = new ArrayList<>();
        IntStream.range(0, scrumCount)
                .forEach(number ->{
                    User user = new User();
                    user.setId( number);
                    user.setUserName("user " + number);
                    user.setUserPassword("user"+System.currentTimeMillis());
                    user.setPhone(""+System.currentTimeMillis());
                    list.add(user);
                });
        return list;
    }
}
