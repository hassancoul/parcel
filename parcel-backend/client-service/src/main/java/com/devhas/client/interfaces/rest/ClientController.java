package com.devhas.client.interfaces.rest;

import com.devhas.client.application.dtos.AjouterAdresseClientRequete;
import com.devhas.client.application.dtos.SupprimerAdresseClientRequete;
import com.devhas.client.application.useCases.AjouterAdresseClientUseCase;
import com.devhas.client.application.useCases.CreerClientUseCase;
import com.devhas.client.application.useCases.RecupererClientUseCase;
import com.devhas.client.application.useCases.SupprimerAdresseClientUseCase;
import com.devhas.client.domain.model.ClientRootEntity;
import com.devhas.client.application.dtos.AdresseDTO;
import com.devhas.client.application.dtos.ClientDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final CreerClientUseCase creerClientUseCase;
    private final RecupererClientUseCase recupererClientUseCase;
    private final AjouterAdresseClientUseCase ajouterAdresseClientUseCase;
    private final SupprimerAdresseClientUseCase supprimerAdresseClientUseCase;

    public ClientController(
            CreerClientUseCase creerClientUseCase,
            RecupererClientUseCase recupererClientUseCase,
            AjouterAdresseClientUseCase ajouterAdresseClientUseCase,
            SupprimerAdresseClientUseCase supprimerAdresseClientUseCase) {
        this.creerClientUseCase = creerClientUseCase;
        this.recupererClientUseCase = recupererClientUseCase;
        this.ajouterAdresseClientUseCase = ajouterAdresseClientUseCase;
        this.supprimerAdresseClientUseCase = supprimerAdresseClientUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> creerClient(@RequestBody ClientDTO clientDTO) {
        creerClientUseCase.execute(clientDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientRootEntity> obtenirClientParId(@PathVariable("clientId") String clientId) {

        ClientRootEntity clientEntity = recupererClientUseCase.execute(clientId);

        return ResponseEntity.ok(clientEntity);
    }

    @PostMapping("{clientId}/adresse")
    public ResponseEntity<Void> ajouterAdresseAuClient(
            @PathVariable("clientId") String clientId,
            @RequestBody AdresseDTO adresseDTO
    ) {

        AjouterAdresseClientRequete requete = new AjouterAdresseClientRequete(
                clientId,
                adresseDTO
        );

        ajouterAdresseClientUseCase.execute(requete);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{clientId}/adresse")
    public ResponseEntity<Void> supprimerAdresseAuClient(
            @PathVariable("clientId") String clientId,
            @RequestBody AdresseDTO adresseDTO
    ) {

        SupprimerAdresseClientRequete requete = new SupprimerAdresseClientRequete(
                clientId,
                adresseDTO
        );

        supprimerAdresseClientUseCase.execute(requete);

        return ResponseEntity.ok().build();
    }
}
