package com.devhas.client.application.useCases;

import com.devhas.client.application.annotation.TransactionalUseCase;
import com.devhas.client.application.annotation.UseCase;
import com.devhas.client.domain.model.ClientId;
import com.devhas.client.domain.model.ClientRootEntity;
import com.devhas.client.domain.repository.ClientRepository;

import java.util.Optional;

@UseCase
public class RecupererClientUseCase implements BaseUseCase<String, ClientRootEntity> {

    private final ClientRepository clientRepository;

    public RecupererClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @TransactionalUseCase(readOnly = true)
    public ClientRootEntity execute(String clientId) {
        Optional<ClientRootEntity> clientOptional = clientRepository.findById(new ClientId(clientId));

        if (clientOptional.isEmpty()) {
            throw new IllegalArgumentException("Client avec l'ID " + clientId + " introuvable");
        }

        return clientOptional.get();
    }
}
