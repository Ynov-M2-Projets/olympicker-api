package com.ynov.olympicker.repositories;

import com.ynov.olympicker.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {
    Page<Event> findByOrganizationId(Long id);
}
