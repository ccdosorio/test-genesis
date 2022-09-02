package com.christian.genesis.clients;

import com.christian.genesis.clients.model.Client;
import com.christian.genesis.clients.service.ClientService;
import com.christian.genesis.model.Department;
import com.christian.genesis.model.MaritalStatus;
import com.christian.genesis.model.Municipality;
import com.christian.genesis.repository.DepartmentRepository;
import com.christian.genesis.repository.MaritalStatusRepository;
import com.christian.genesis.repository.MunicipalityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/client")
public class ClientController {

    private final ClientService clientService;
    private final DepartmentRepository departmentRepository;
    private final MaritalStatusRepository maritalStatusRepository;
    private final MunicipalityRepository municipalityRepository;

    public ClientController(
            final ClientService clientService,
            final DepartmentRepository departmentRepository,
            final MaritalStatusRepository maritalStatusRepository,
            final MunicipalityRepository municipalityRepository
    ) {
        this.clientService = clientService;
        this.departmentRepository = departmentRepository;
        this.maritalStatusRepository = maritalStatusRepository;
        this.municipalityRepository = municipalityRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getClients() {
        return new ResponseEntity<>(clientService.getClients(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClient(@PathVariable final Long id) {
        return clientService.getClient(id)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> save(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }

    @PostMapping(
            value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Client> update(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.update(client), HttpStatus.CREATED);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable final Long id) {
        if (clientService.delete(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    // catalogs

    @GetMapping(value = "/department", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    @GetMapping(value = "/municipality", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Municipality> getMunicipalities() {
        return municipalityRepository.findAll();
    }

    @GetMapping(value = "/marital", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MaritalStatus> getMaritalStatus() {
        return maritalStatusRepository.findAll();
    }
}
