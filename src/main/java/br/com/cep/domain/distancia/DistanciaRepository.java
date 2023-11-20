package br.com.cep.domain.distancia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DistanciaRepository extends JpaRepository<Distancia, Long> {
    Distancia findAllByCepOrigem(String cep);
    Distancia findAllByCepDestino(String cep);
}
