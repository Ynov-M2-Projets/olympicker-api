package com.ynov.olympicker.services;

import com.ynov.olympicker.dto.CreateOrganizationDTO;
import com.ynov.olympicker.entities.Organization;
import com.ynov.olympicker.entities.User;
import com.ynov.olympicker.repositories.OrganizationRepository;
import com.ynov.olympicker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private UserRepository userRepository;


    public Organization getOrganizationById(Long id) {
        return organizationRepository.findById(id).orElse(null);
    }

    public List<Organization> getAllOrganizations(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        return organizationRepository.findAll(paging).getContent();
    }

    public Organization createOrganization(User user, CreateOrganizationDTO orga) {
        Organization organization = new Organization();
        organization.setOwner(user);
        organization.setName(orga.getName());
        organization.setDescription(orga.getDescription());
        return organizationRepository.save(organization);
    }


    public Organization updateOrganization(Long id, CreateOrganizationDTO orga) {
        Organization organization = organizationRepository.findById(id).orElse(null);
        if (organization == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organization not found");
        organization.setName(orga.getName());
        organization.setDescription(orga.getDescription());
        return organizationRepository.save(organization);
    }

    public Organization changeOrganizationOwner(Long orgId, Long userId) {
        Organization organization = organizationRepository.findById(orgId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (organization == null || user == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organization or User not found");
        organization.setOwner(user);
        return organizationRepository.save(organization);
    }

    public List<User> getAllMembers(Long id) {
        Organization organization = organizationRepository.findById(id).orElse(null);
        if (organization == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organization not found");
        return organization.getMembers();
    }
}
