package com.devhas.client.domain.repository;

import com.devhas.client.domain.model.ClientRootEntity;
import com.devhas.client.domain.model.ClientId;

import java.util.Optional;

public interface ClientRepository {
    Optional<ClientRootEntity> findById(ClientId clientId);
    void save(ClientRootEntity client);
}
