package com.devhas.client.infrastructure.persistance;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "client_adresses")
public class JpaAdresse {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String clientId;
    private String nom;
    private String rue;
    private String ville;
    private String codePostal;
    private String pays;
    private Boolean estSupprime;
}
