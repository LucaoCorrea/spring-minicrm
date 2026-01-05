package com.spring.minicrm.controller;

import com.spring.minicrm.dto.ApiResponse;
import com.spring.minicrm.model.ContactModel;
import com.spring.minicrm.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients/{clientId}/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ContactModel>> create(
            @PathVariable Long clientId,
            @RequestBody ContactModel contact) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Contato criado com sucesso",
                        contactService.create(clientId, contact)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ContactModel>>> list(@PathVariable Long clientId) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Contatos listados com sucesso",
                        contactService.listByClient(clientId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        contactService.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Contato removido com sucesso", null));
    }
}