package com.devhas.client.domain.model;

import org.springframework.util.Assert;

import java.util.regex.Pattern;

public record Email(String email) {

    public Email {
        Assert.notNull(email, "L'email est obligatoire");
        Assert.isTrue(
                Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n", email),
                "Le format de l'email est invalide"
        );
    }
}
