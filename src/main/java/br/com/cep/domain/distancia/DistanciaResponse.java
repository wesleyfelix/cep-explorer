package br.com.cep.domain.distancia;

public record DistanciaResponse(String distanciaPe, String distanciaCarro, String url) {
    public DistanciaResponse(Distancia distancia){

        this(distancia.getDistanciaPe(), distancia.getDistanciaCarro(), distancia.getUrl());
    }
}
