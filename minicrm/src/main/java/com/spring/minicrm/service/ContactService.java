package com.spring.minicrm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.minicrm.model.ClientModel;
import com.spring.minicrm.model.ContactModel;
import com.spring.minicrm.repository.ClientRepository;
import com.spring.minicrm.repository.ContactRepository;

@Service
public class ContactService {
    
    private final ContactRepository contactRepository;
    private final ClientRepository clientRepository;

    public ContactService(ContactRepository contactRepository, ClientRepository clientRepository) {
        this.contactRepository = contactRepository;
        this.clientRepository = clientRepository;
    }

    public ContactModel create(Long clientId, ContactModel contact) {
        ClientModel client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
            
        contact.setClient(client);
        return contactRepository.save(contact);
    }

     public List<ContactModel> listByClient(Long clientId) {
        return contactRepository.findByClientId(clientId);
    }

    public void delete(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new RuntimeException("Contact not found");
        }
        contactRepository.deleteById(id);
    }

}