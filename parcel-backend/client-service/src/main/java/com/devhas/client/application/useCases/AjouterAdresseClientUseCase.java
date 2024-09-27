package com.devhas.client.application.useCases;

import com.devhas.client.application.annotation.TransactionalUseCase;
import com.devhas.client.application.annotation.UseCase;
import com.devhas.client.application.dtos.AjouterAdresseClientRequete;
import com.devhas.client.domain.model.Adresse;
import com.devhas.client.domain.model.ClientId;
import com.devhas.client.domain.model.ClientRootEntity;
import com.devhas.client.domain.repository.ClientRepository;

import java.util.Optional;

@UseCase
public class AjouterAdresseClientUseCase implements BaseUseCase<AjouterAdresseClientRequete, Void> {

    private final ClientRepository clientRepository;

    public AjouterAdresseClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @TransactionalUseCase
    public Void execute(AjouterAdresseClientRequete adresseClientRequete) {

        Optional<ClientRootEntity> clientOptional = clientRepository.findById(
                new ClientId(adresseClientRequete.clientId())
        );

        if (clientOptional.isEmpty()) {
            throw new IllegalArgumentException("Client avec l'ID " + adresseClientRequete.clientId() + " introuvable");
        }

        ClientRootEntity client = clientOptional.get();

        client.ajouterAdresse(new Adresse(
                adresseClientRequete.adresseDTO().nom(),
                adresseClientRequete.adresseDTO().rue(),
                adresseClientRequete.adresseDTO().ville(),
                adresseClientRequete.adresseDTO().codePostal(),
                adresseClientRequete.adresseDTO().pays()
        ));

        clientRepository.save(client);

        return null;
    }
}
