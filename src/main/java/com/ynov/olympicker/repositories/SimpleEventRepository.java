package com.ynov.olympicker.repositories;

import com.ynov.olympicker.entities.SimpleEvent;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleEventRepository extends PagingAndSortingRepository<SimpleEvent, Long> {
}
