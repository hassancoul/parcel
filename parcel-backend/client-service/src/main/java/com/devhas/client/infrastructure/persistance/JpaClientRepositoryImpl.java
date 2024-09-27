package com.devhas.client.infrastructure.persistance;

import com.devhas.client.domain.model.ClientId;
import com.devhas.client.domain.model.ClientRootEntity;
import com.devhas.client.domain.repository.ClientRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaClientRepositoryImpl implements ClientRepository {

    private final JpaClientRepository jpaClientRepository;

    public JpaClientRepositoryImpl(JpaClientRepository jpaClientRepository) {
        this.jpaClientRepository = jpaClientRepository;
    }

    @Override
    public Optional<ClientRootEntity> findById(ClientId clientId) {
        Optional<JpaClientEntity> optionalJpaClientEntity = jpaClientRepository.findById(clientId.clientId());

        if (optionalJpaClientEntity.isEmpty()) {
            return Optional.empty();
        }

        ClientRootEntity clientRootEntity = ClientMapper.toDomainEntity(optionalJpaClientEntity.get());

        return Optional.of(clientRootEntity);
    }

    @Override
    public void save(ClientRootEntity client) {
        JpaClientEntity jpaEntity = ClientMapper.toJpaEntity(client);

        jpaClientRepository.save(jpaEntity);
    }
}
