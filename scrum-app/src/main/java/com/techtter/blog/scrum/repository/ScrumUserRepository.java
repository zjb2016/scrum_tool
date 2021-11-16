package com.techtter.blog.scrum.repository;

import com.techtter.blog.scrum.model.ScrumUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScrumUserRepository extends CrudRepository<ScrumUser, Long> {

    Optional<ScrumUser> findByPhone(String phone);

    void deleteByPhone(String phone);

}
