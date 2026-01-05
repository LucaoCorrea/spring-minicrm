package com.spring.minicrm.repository;

import com.spring.minicrm.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientModel, Long> {

    boolean existsByEmail(String email);
}
