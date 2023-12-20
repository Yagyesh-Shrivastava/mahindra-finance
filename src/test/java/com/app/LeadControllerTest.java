package com.app;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.controller.LeadController;
import com.app.exception.ErrorResponse;
import com.app.pojo.Lead;
import com.app.service.LeadService;

@RunWith(MockitoJUnitRunner.class)
public class LeadControllerTest {

    @Mock
    private LeadService leadService;

    @InjectMocks
    private LeadController leadController;

    @Test
    public void testCreateLead_Success() {
        Lead lead = new Lead(); // Create a lead object for testing
        when(leadService.createLead(any(Lead.class))).thenReturn(lead);

        ResponseEntity<Object> responseEntity = leadController.createLead(lead);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
    
    @Test
    public void testGetLeadsByMobileNumber_Success() {
        List<Lead> leads = new ArrayList<>();
        // Create sample leads for testing
        Lead lead1 = new Lead(/* lead details */);
        Lead lead2 = new Lead(/* lead details */);
        leads.add(lead1);
        leads.add(lead2);
        
        when(leadService.getLeadsByMobileNumber("8877887788")).thenReturn(leads);

        ResponseEntity<Object> responseEntity = leadController.getLeadsByMobileNumber("8877887788");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // Add assertions for the response entity and its content
    }

    @Test
    public void testGetLeadsByMobileNumber_NoLeadFound() {
        when(leadService.getLeadsByMobileNumber("9999999999")).thenReturn(Collections.emptyList());

        ResponseEntity<Object> responseEntity = leadController.getLeadsByMobileNumber("9999999999");

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        ErrorResponse errorResponse = (ErrorResponse) responseEntity.getBody();
        assertEquals("E10011", errorResponse.getCode());
    }


}
