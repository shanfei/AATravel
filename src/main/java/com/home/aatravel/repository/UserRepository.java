package com.home.aatravel.repository;

import com.home.aatravel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    @Query(" from User u where u.email = :emailOrUserName or u.username = :emailOrUserName")
    Optional<User> findByEmailOrUsername(String emailOrUserName);

    boolean existsUserByEmail(String email);
}
