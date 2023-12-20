package com.app.repository;

import com.app.pojo.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
	List<Lead> findByMobileNumber(String mobileNumber);
    Lead findByEmail(String email);
}
