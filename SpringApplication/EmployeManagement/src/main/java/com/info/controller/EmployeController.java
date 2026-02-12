package com.info.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.info.dto.EmployeDTO;
import com.info.dto.EmployeResponseDTO;
import com.info.service.EmployeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employe")
public class EmployeController {

    private final EmployeService service;

    public EmployeController(EmployeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EmployeDTO> save(@Valid @RequestBody EmployeDTO dto) {
        return ResponseEntity.ok(service.saveEmploye(dto));
    }

   
    @GetMapping("/all")
    public ResponseEntity<Page<EmployeDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(
                service.findAll(page, size, sortBy, direction)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok("Employe deleted Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeResponseDTO> update(
            @PathVariable Integer id,
            @RequestBody EmployeResponseDTO dto) {
        return ResponseEntity.ok(service.updateEmploye(id, dto));
    }
}
