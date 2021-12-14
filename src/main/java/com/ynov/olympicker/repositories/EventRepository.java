package com.ynov.olympicker.repositories;

import com.ynov.olympicker.entities.Event;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository<T extends  Event> extends PagingAndSortingRepository<T, Long> {
}
