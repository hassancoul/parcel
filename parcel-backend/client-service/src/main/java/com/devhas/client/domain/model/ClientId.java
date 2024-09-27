package com.devhas.client.domain.model;

import lombok.Getter;
import org.springframework.util.Assert;

public record ClientId(String clientId) {

    public ClientId {
        Assert.notNull(clientId, "L'ID client est obligatoire");
    }
}
