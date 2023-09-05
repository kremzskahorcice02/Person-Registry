package org.example.controllers;

import org.example.dtos.PersonDetailsResponse;
import org.example.dtos.PersonIdRequest;
import org.example.dtos.StoreRecordDetails;
import org.example.services.RegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/person")
public class RegistryController {

    private final RegistryService registryService;

    @Autowired
    public RegistryController(RegistryService registryService) {
        this.registryService = registryService;
    }

    @PostMapping
    public ResponseEntity<PersonDetailsResponse> createRecord(@RequestBody StoreRecordDetails personDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(registryService.create(personDetails));
    }

    @DeleteMapping
    public ResponseEntity<PersonDetailsResponse> deleteRecord(@RequestBody PersonIdRequest idRequest) {
        return ResponseEntity.ok().body(registryService.delete(idRequest));
    }

    @GetMapping
    public ResponseEntity<PersonDetailsResponse> searchRecord(@RequestBody PersonIdRequest idRequest) {
        return ResponseEntity.ok().body(registryService.search(idRequest));
    }
}