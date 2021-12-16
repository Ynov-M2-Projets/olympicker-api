package com.ynov.olympicker.controllers;

import com.ynov.olympicker.dto.CreateOrganizationDTO;
import com.ynov.olympicker.entities.Event;
import com.ynov.olympicker.entities.Organization;
import com.ynov.olympicker.entities.User;
import com.ynov.olympicker.services.AuthService;
import com.ynov.olympicker.services.OrganizationService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/orgs")
@RestController
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private AuthService authService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Organization> getAllOrgs(@ParameterObject @PageableDefault(size = 25) Pageable pageable) {
        return organizationService.getAllOrganizations(pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Organization getOrgById(@PathVariable Long id) {
        return this.organizationService.getOrganizationById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Organization createOrg(@RequestBody CreateOrganizationDTO org, Principal principal) {
        User user = authService.whoami(principal);
        return this.organizationService.createOrganization(user, org);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @PreAuthorize("authService.whoami(principal).id == organizationService.getOrganizationById(#id).owner.id")
    public Organization updateOrg(@PathVariable Long id, @RequestBody CreateOrganizationDTO org, Principal principal) {
        return this.organizationService.updateOrganization(id, org);
    }


    @PreAuthorize("authService.whoami(principal).id == organizationService.getOrganizationById(#id).owner.id")
    @RequestMapping(value = "/{id}/changeOwner/{userId}", method = RequestMethod.PUT)
    public Organization changeOrganizationOwner(@PathVariable Long id, @PathVariable Long userId, Principal principal) {
        return this.organizationService.changeOrganizationOwner(id, userId);
    }

    @RequestMapping(value = "/{id}/members", method = RequestMethod.GET)
    public List<User> getAllMembers(@PathVariable Long id) {
        return this.organizationService.getAllMembers(id);
    }


    @PreAuthorize("authService.whoami(principal).id == organizationService.getOrganizationById(#id).owner.id")
    @RequestMapping(value = "/{id}/add_member/{userId}", method = RequestMethod.PUT)
    public Organization addMember(@PathVariable Long id, @PathVariable Long userId, Principal principal) {
        return this.organizationService.addMember(id, userId);
    }

    @PreAuthorize("authService.whoami(principal).id == organizationService.getOrganizationById(#id).owner.id")
    @RequestMapping(value = "/{id}/remove_member/{userId}", method = RequestMethod.PUT)
    public Organization remove(@PathVariable Long id, @PathVariable Long userId, Principal principal) {
        return this.organizationService.removeMember(id, userId);
    }

    @RequestMapping(value = "/{id}/events", method = RequestMethod.GET)
    public List<Event> getOrganizationEvents(@PathVariable Long id) {
        return this.organizationService.getOrganizationEvents(id);
    }

}
