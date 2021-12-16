package com.ynov.olympicker.repositories;

import com.ynov.olympicker.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepositoryNoPagination extends JpaRepository<Event, Long> {
    List<Event> findByOrganizationId(Long id);
}
