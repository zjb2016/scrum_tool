package com.techtter.blog.scrum.repository;

import com.techtter.blog.scrum.model.ScrumUserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScrumUserRoleRepository extends CrudRepository<ScrumUserRole, Long> {

    Optional<ScrumUserRole> findByRoleName(String roleName);

    void deleteByRoleName(String roleName);

}
