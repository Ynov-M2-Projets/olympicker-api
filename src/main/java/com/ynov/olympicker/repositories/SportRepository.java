package com.ynov.olympicker.repositories;

import com.ynov.olympicker.entities.Sport;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SportRepository extends PagingAndSortingRepository<Sport, Long> {
}
