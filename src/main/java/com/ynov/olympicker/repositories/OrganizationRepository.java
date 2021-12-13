package com.ynov.olympicker.repositories;

import com.ynov.olympicker.entities.Organization;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Long> {
}
