package com.spring.minicrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.minicrm.model.ContactModel;

public interface ContactRepository extends JpaRepository<ContactModel, Long> {
    List<ContactModel> findByClientId(Long clientId);
}
