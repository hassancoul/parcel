package com.devhas.client.application.useCases;

import com.devhas.client.application.annotation.TransactionalUseCase;
import com.devhas.client.application.annotation.UseCase;
import com.devhas.client.application.dtos.ClientDTO;
import com.devhas.client.domain.model.ClientId;
import com.devhas.client.domain.model.ClientRootEntity;
import com.devhas.client.domain.model.Email;
import com.devhas.client.domain.model.Username;
import com.devhas.client.domain.repository.ClientRepository;

import java.util.Collections;

@UseCase
public class CreerClientUseCase implements BaseUseCase<ClientDTO, Void> {

    private final ClientRepository clientRepository;

    public CreerClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @TransactionalUseCase()
    public Void execute(ClientDTO clientDTO) {

        ClientId clientId = new ClientId(clientDTO.id());
        Username clientUsername = new Username(clientDTO.username());
        Email clientEmail = new Email(clientDTO.email());

        if (clientRepository.findById(clientId).isPresent()) {
            throw new IllegalArgumentException("Le client existe déjà !");
        }

        ClientRootEntity client = new ClientRootEntity(
                clientId,
                clientUsername,
                clientEmail,
                Collections.emptyList());

        clientRepository.save(client);

        return null;
    }
}
