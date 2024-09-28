package com.devhas.client.domain.model;


import java.util.Objects;

public record Adresse(String nom, String rue, String ville, String codePostal, String pays, Boolean estSupprime) {

    public Adresse(String nom, String rue, String ville, String codePostal, String pays) {
        this(nom, rue, ville, codePostal, pays, false);
    }

    public Adresse {
        if (Objects.isNull(rue) || rue.trim().isEmpty()) {
            throw new IllegalArgumentException("Le champ rue ne doit pas être null dans l'adresse");
        }
        if (Objects.isNull(ville) || ville.trim().isEmpty()) {
            throw new IllegalArgumentException("Le champ ville ne doit pas être null dans l'adresse");
        }
        if (Objects.isNull(codePostal) || codePostal.trim().isEmpty()) {
            throw new IllegalArgumentException("Le champ code postal ne doit pas être null dans l'adresse");
        }
        if (Objects.isNull(pays) || pays.trim().isEmpty()) {
            throw new IllegalArgumentException("Le champ pays ne doit pas être null dans l'adresse");
        }
    }

    public Adresse markAsDeleted() {
        return new Adresse(this.nom, this.rue, this.ville, this.codePostal, this.pays, true);
    }

    public Boolean estSupprime() {
        return this.estSupprime;
    }
}
