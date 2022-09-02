package com.christian.genesis.repository;

import com.christian.genesis.clients.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "select c from Client c where c.dpi=?1")
    Optional<Client> findClientByDpi(String dpi);

    @Query(value = "update Client c set c.state = 'Inactive' where c.id=?1")
    void inactive(Long id);

    @Query(value = "select c from Client c where c.state = 'Active'")
    List<Client> findClientByState();
}
