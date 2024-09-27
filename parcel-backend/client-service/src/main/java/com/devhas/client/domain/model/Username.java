package com.devhas.client.domain.model;

import lombok.Getter;
import org.springframework.util.Assert;

public record Username(String username) {

    public Username {
        Assert.notNull(username, "Le username est obligatoire");
    }
}
