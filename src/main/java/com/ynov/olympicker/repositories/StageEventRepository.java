package com.ynov.olympicker.repositories;

import com.ynov.olympicker.entities.StageEvent;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageEventRepository extends PagingAndSortingRepository<StageEvent, Long> {
}
