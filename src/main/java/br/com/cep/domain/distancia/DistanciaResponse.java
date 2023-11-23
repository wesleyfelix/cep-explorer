package br.com.cep.domain.distancia;

public record DistanciaResponse(String distancia, String url) {
    public DistanciaResponse(Distancia distancia){
        this(distancia.getDistancia(), distancia.getUrl());
    }
}
