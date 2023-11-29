package br.com.cep.domain.distancia;

public record DistanciasTipo(String pe, String carro) {
    public DistanciasTipo {
        if (pe == null || carro == null) {
            throw new IllegalArgumentException("Os valores não podem ser nulos.");
        }
    }
}
