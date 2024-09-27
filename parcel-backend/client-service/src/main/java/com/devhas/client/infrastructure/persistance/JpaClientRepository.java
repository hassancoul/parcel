package com.devhas.client.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaClientRepository extends JpaRepository<JpaClientEntity, String> {
}
