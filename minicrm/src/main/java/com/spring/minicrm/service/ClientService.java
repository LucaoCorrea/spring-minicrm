package com.spring.minicrm.service;

import com.spring.minicrm.exception.ClientNotFoundException;
import com.spring.minicrm.exception.EmailAlreadyExistsException;
import com.spring.minicrm.model.ClientModel;
import com.spring.minicrm.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientModel create(ClientModel client) {
        if (clientRepository.existsByEmail(client.getEmail())) {
            throw new EmailAlreadyExistsException();
        }
        return clientRepository.save(client);
    }

    public List<ClientModel> list() {
        return clientRepository.findAll();
    }

    public ClientModel findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(ClientNotFoundException::new);
    }

    public ClientModel update(Long id, ClientModel client) {
        ClientModel existing = findById(id);
        existing.setName(client.getName());
        existing.setEmail(client.getEmail());
        return clientRepository.save(existing);
    }

    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException();
        }
        clientRepository.deleteById(id);
    }
}
