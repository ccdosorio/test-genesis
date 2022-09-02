package com.christian.genesis.clients.service;

import com.christian.genesis.clients.model.Client;
import com.christian.genesis.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findClientByState();
    }

    public Optional<Client> getClient(final Long id) {
        return clientRepository.findById(id);
    }

    public Client save(Client client) {
        Optional<Client> clientOptional = clientRepository.findClientByDpi(client.getDpi());
        if (clientOptional.isPresent()) {
            throw new IllegalStateException("client exists");
        }
        return clientRepository.save(client);
    }

    public Client update(Client client) {
        Optional<Client> clientOptional = clientRepository.findById(client.getId());
        Client newClient = new Client();
        if (!clientOptional.isPresent()) {
            throw new IllegalStateException("client not exists");
        }
        newClient.setAddress(client.getAddress());
        newClient.setBirthday(client.getBirthday());
        newClient.setDepartment(client.getDepartment());
        newClient.setMunicipality(client.getMunicipality());
        newClient.setMaritalStatus(client.getMaritalStatus());
        newClient.setName(client.getName());
        newClient.setPhone(client.getPhone());
        return clientRepository.save(newClient);
    }

    public boolean delete(final Long id) {
        return getClient(id).map(client -> {
            clientRepository.inactive(id);
            return true;
        }).orElse(false);
    }

}
