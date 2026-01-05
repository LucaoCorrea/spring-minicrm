package com.spring.minicrm.controller;

import com.spring.minicrm.dto.*;
import com.spring.minicrm.model.ClientModel;
import com.spring.minicrm.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClientModel>> create(@RequestBody ClientModel client) {
        ClientModel created = clientService.create(client);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Cliente criado com sucesso", created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientModel>>> list() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Clientes listados com sucesso", clientService.list()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientModel>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Cliente encontrado com sucesso", clientService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientModel>> update(
            @PathVariable Long id,
            @RequestBody ClientModel client) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Cliente atualizado com sucesso", clientService.update(id, client)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Cliente removido com sucesso", null));
    }
}
