package com.devhas.client.domain.model;

import org.springframework.util.Assert;

public record Adresse(String nom, String rue, String ville, String codePostal, String pays, Boolean estSupprime) {

    public Adresse(String nom, String rue, String ville, String codePostal, String pays) {
        this(nom, rue, ville, codePostal, pays, false);
    }

    public Adresse {
        Assert.notNull(rue, "Le champ rue ne doit pas être null dans l'adresse should not be null in address");
        Assert.notNull(ville, "Le champ ville ne doit pas être null dans l'adresse");
        Assert.notNull(codePostal, "Le champ code postal ne doit pas être null dans l'adresse");
        Assert.notNull(pays, "Le champ pays ne doit pas être null dans l'adresse");
    }

    public Adresse markAsDeleted() {
        return new Adresse(this.nom, this.rue, this.ville, this.codePostal, this.pays, true);
    }

    public Boolean estSupprime() {
        return this.estSupprime;
    }
}
