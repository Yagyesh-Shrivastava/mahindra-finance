package com.app.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.ErrorResponse;
import com.app.exception.LeadExistsException;
import com.app.exception.LeadNotFoundException;
import com.app.pojo.Lead;
import com.app.service.LeadService;

@RestController
@RequestMapping("/api/leads")
public class LeadController {
    @Autowired
    private LeadService leadService;

    @PostMapping("/create")
    public ResponseEntity<Object> createLead(@RequestBody @Valid Lead lead) {
        try {
            Lead createdLead = leadService.createLead(lead);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLead);
        } catch (LeadExistsException e) {
            ErrorResponse errorResponse = new ErrorResponse("E10010", Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/{leadId}")
    public ResponseEntity<Object> getLeadById(@PathVariable("leadId") Long leadId) {
        Lead lead = leadService.getLeadById(leadId);
        if (lead != null) {
            return ResponseEntity.ok().body(lead);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("E10011", Collections.singletonList("Lead not found"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PutMapping("/{leadId}")
    public ResponseEntity<Object> updateLead(@PathVariable("leadId") Long leadId, @RequestBody @Valid Lead lead) {
        try {
            Lead updatedLead = leadService.updateLead(leadId, lead);
            return ResponseEntity.ok().body(updatedLead);
        } catch (LeadNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse("E10012", Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @DeleteMapping("/{leadId}")
    public ResponseEntity<Object> deleteLead(@PathVariable("leadId") Long leadId) {
        try {
            leadService.deleteLead(leadId);
            return ResponseEntity.ok().build();
        } catch (LeadNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse("E10013", Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
    
    @GetMapping("/fetch/{mobileNumber}")
    public ResponseEntity<Object> getLeadsByMobileNumber(@PathVariable String mobileNumber) {
        List<Lead> leads = leadService.getLeadsByMobileNumber(mobileNumber);
        if (leads.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse("E10011", Collections.singletonList("No Lead found with the Mobile Number"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } else {
            return ResponseEntity.ok().body(prepareResponse("success", leads));
        }
    }

    private Map<String, Object> prepareResponse(String status, List<Lead> leads) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("data", leads);
        return response;
    }
}


