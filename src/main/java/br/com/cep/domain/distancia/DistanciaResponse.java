package br.com.cep.domain.distancia;

public record DistanciaResponse(String distancia) {
    public DistanciaResponse(Distancia distancia){
        this(distancia.getDistancia());
    }
}
