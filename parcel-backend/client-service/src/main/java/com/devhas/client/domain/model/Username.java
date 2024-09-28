package com.devhas.client.domain.model;


import java.util.Objects;

public record Username(String username) {

    public Username {
        if (Objects.isNull(username) || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Le username est obligatoire et ne peut être vide");
        }
    }
}
