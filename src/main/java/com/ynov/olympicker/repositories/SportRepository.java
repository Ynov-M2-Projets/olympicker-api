package com.ynov.olympicker.repositories;

import com.ynov.olympicker.entities.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportRepository extends JpaRepository<Sport, Long> {
}
