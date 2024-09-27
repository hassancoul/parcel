package com.devhas.client.infrastructure.persistance;

import com.devhas.client.domain.model.*;

public class ClientMapper {

    public static ClientRootEntity toDomainEntity(JpaClientEntity jpaClientEntity) {
        return new ClientRootEntity(
                new ClientId(jpaClientEntity.getClientId()),
                new Username(jpaClientEntity.getUsername()),
                new Email(jpaClientEntity.getEmail()),
                jpaClientEntity.getAdresses().stream().map(jpaAdresse -> new Adresse(
                        jpaAdresse.getNom(),
                        jpaAdresse.getRue(),
                        jpaAdresse.getVille(),
                        jpaAdresse.getCodePostal(),
                        jpaAdresse.getPays(),
                        jpaAdresse.getEstSupprime()
                )).toList()
        );
    }


    public static JpaClientEntity toJpaEntity(ClientRootEntity clientRootEntity) {
        JpaClientEntity jpaClientEntity = new JpaClientEntity();
        jpaClientEntity.setClientId(clientRootEntity.getClientId().clientId());
        jpaClientEntity.setUsername(clientRootEntity.getUsername().username());
        jpaClientEntity.setEmail(clientRootEntity.getUsername().username());
        jpaClientEntity.setAdresses(clientRootEntity.getAdresses().stream().map(adresse -> {
            JpaAdresse jpaAdresse = new JpaAdresse();
            jpaAdresse.setClientId(clientRootEntity.getClientId().clientId());
            jpaAdresse.setNom(adresse.nom());
            jpaAdresse.setVille(adresse.ville());
            jpaAdresse.setVille(adresse.ville());
            jpaAdresse.setCodePostal(adresse.codePostal());
            jpaAdresse.setPays(adresse.pays());
            jpaAdresse.setEstSupprime(adresse.estSupprime());

            return jpaAdresse;
        }).toList());

        return jpaClientEntity;
    }
}
