package com.example.g130ministore.repository;

import com.example.g130ministore.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = "roles")
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsernameWithRoles(String username);

    Optional<User> findByUsername(String username);

}
