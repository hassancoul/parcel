package com.devhas.client.domain.model;

import lombok.Getter;

import java.util.List;

public class ClientRootEntity {

    @Getter
    private final ClientId clientId;
    @Getter
    private final Username username;
    @Getter
    private final Email email;
    private List<Adresse> adresses;

    public ClientRootEntity(ClientId clientId, Username username, Email email, List<Adresse> adresses) {
        this.clientId = clientId;
        this.username = username;
        this.email = email;
        this.adresses = adresses;
    }

    public List<Adresse> getAdresses() {
        List<Adresse> listAdresse = this.adresses.stream()
                .filter(address -> !address.estSupprime())
                .toList();

        return listAdresse;
    }

    public void ajouterAdresse(Adresse nouvelleAdresse) {
        this.adresses.add(nouvelleAdresse);
    }

    public void supprimerAdresse(Adresse adresseASupprimer) {

        this.adresses = this.adresses.stream()
                .map(address -> address.equals(adresseASupprimer) ? address.markAsDeleted() : address)
                .toList();
    }
}
