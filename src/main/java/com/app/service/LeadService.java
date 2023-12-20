package com.app.service;

import com.app.pojo.*;
import com.app.exception.LeadExistsException;
import com.app.exception.LeadNotFoundException;
import com.app.repository.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeadService {
    @Autowired
    private LeadRepository leadRepository;

    public Lead createLead(Lead lead) {
    	 Lead existingLead = leadRepository.findByEmail(lead.getEmail());
         if (existingLead != null) {
             throw new LeadExistsException("Lead Already Exists in the database with the provided email");
         }
        return leadRepository.save(lead);
    }
    
    public Lead getLeadById(Long leadId) {
        Optional<Lead> leadOptional = leadRepository.findById(leadId);
        return leadOptional.orElse(null);
    }
    
    public Lead updateLead(Long leadId, Lead lead) {
        Optional<Lead> existingLeadOptional = leadRepository.findById(leadId);
        
        if (existingLeadOptional.isPresent()) {
            Lead existingLead = existingLeadOptional.get();
            existingLead.setFirstName(lead.getFirstName()); 
            existingLead.setLastName(lead.getLastName());

            return leadRepository.save(existingLead);
        } else {
            throw new LeadNotFoundException("Lead not found for id: " + leadId);
        }
    }
    
    
    public void deleteLead(Long leadId) {
        Optional<Lead> leadOptional = leadRepository.findById(leadId);
        if (leadOptional.isPresent()) {
            leadRepository.delete(leadOptional.get());
        } else {
            throw new LeadNotFoundException("Lead not found for id: " + leadId);
        }
    }
    
    public List<Lead> getLeadsByMobileNumber(String mobileNumber) {
        return leadRepository.findByMobileNumber(mobileNumber);
    }
}
