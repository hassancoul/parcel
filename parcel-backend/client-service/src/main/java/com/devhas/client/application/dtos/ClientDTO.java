package com.devhas.client.application.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public record ClientDTO(String id, String username, String email) {
}
