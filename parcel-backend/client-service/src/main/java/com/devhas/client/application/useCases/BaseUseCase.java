package com.devhas.client.application.useCases;

public interface BaseUseCase<TRequete, TResponse> {
    TResponse execute(TRequete requete);
}
