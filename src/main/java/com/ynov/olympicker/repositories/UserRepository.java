package com.ynov.olympicker.repositories;

import com.ynov.olympicker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

}
