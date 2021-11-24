package com.techtter.blog.scrum.repository;

import com.techtter.blog.scrum.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScrumUserRepository extends CrudRepository<User, Long> {

    Optional<User> findByPhone(String phone);

    void deleteByPhone(String phone);

}
