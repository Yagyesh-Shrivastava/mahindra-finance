package com.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.app.repository.LeadRepository;
import com.app.service.LeadService;
import com.app.exception.LeadExistsException;
import com.app.pojo.Lead;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LeadServiceTest {

    @Mock
    private LeadRepository leadRepository;

    @InjectMocks
    private LeadService leadService;

    @Test
    public void testCreateLead_LeadDoesNotExist() {
        Lead lead = new Lead(); 
        when(leadRepository.findByEmail(anyString())).thenReturn(null);
        when(leadRepository.save(any(Lead.class))).thenReturn(lead);

        Lead createdLead = leadService.createLead(lead);

        assertNotNull(createdLead);
        // Add more assertions as needed
    }

    @Test
    public void testCreateLead_LeadExists() {
        Lead lead = new Lead(); 
        when(leadRepository.findByEmail(anyString())).thenReturn(lead); 
        try {
            leadService.createLead(lead);
            fail("Expected LeadExistsException was not thrown");
        } catch (LeadExistsException e) {
            assertEquals("Lead already exists", e.getMessage());
        }
    }

}
