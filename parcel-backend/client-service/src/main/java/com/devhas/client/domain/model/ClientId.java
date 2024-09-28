package com.devhas.client.domain.model;


import java.util.Objects;

public record ClientId(String clientId) {

    public ClientId {
        if (Objects.isNull(clientId) || clientId.trim().isEmpty()) {
            throw new IllegalArgumentException("L'ID client est obligatoire et ne peut être vide");
        }
    }
}
