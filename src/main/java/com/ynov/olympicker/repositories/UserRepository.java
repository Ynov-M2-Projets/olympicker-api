package com.ynov.olympicker.repositories;

import com.ynov.olympicker.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    public User findByEmail(String email);

}
