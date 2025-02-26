package com.example.g130ministore.repository;

import com.example.g130ministore.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.name = 'ROLE_USER'")
    Role findRoleUser();

    Optional<Role> findByName(String name);
}
