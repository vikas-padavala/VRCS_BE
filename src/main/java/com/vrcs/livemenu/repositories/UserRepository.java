package com.vrcs.livemenu.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vrcs.livemenu.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);

    public Optional<User> findById(Long id);
    // public Optional<User> findByEmailOptional(String email);

    // public Optional<User> findByUsernameOptional(String username);
}
