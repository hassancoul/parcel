package com.devhas.client.infrastructure.config;

import com.devhas.client.application.useCases.AjouterAdresseClientUseCase;
import com.devhas.client.application.useCases.CreerClientUseCase;
import com.devhas.client.application.useCases.RecupererClientUseCase;
import com.devhas.client.application.useCases.SupprimerAdresseClientUseCase;
import com.devhas.client.domain.repository.ClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public CreerClientUseCase creerClientUseCase(ClientRepository clientRepository) {
        return new CreerClientUseCase(clientRepository);
    }

    @Bean
    public RecupererClientUseCase recupererClientUseCase(ClientRepository clientRepository) {
        return new RecupererClientUseCase(clientRepository);
    }

    @Bean
    public AjouterAdresseClientUseCase ajouterAdresseClientUseCase(ClientRepository clientRepository) {
        return new AjouterAdresseClientUseCase(clientRepository);
    }

    @Bean
    public SupprimerAdresseClientUseCase supprimerAdresseClientUseCase(ClientRepository clientRepository) {
        return new SupprimerAdresseClientUseCase(clientRepository);
    }
}
