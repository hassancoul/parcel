package com.devhas.client.domain.model;


import java.util.Objects;
import java.util.regex.Pattern;

public record Email(String email) {

    public Email {
        if (Objects.isNull(email) || email.trim().isEmpty()) {
            throw new IllegalArgumentException("L'email est obligatoire et ne peut être vide");
        }
        if (!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n", email)) {
            throw new IllegalArgumentException("Le format de l'email est invalide");
        }
    }
}
