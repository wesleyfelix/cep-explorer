package br.com.cep.domain.distancia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DistanciaRepository extends JpaRepository<Distancia, Long> {
    Distancia findAllByCepOrigem(String cep);
    Distancia findAllByCepDestino(String cep);

    @Query("""
    select d from Distancia d where 
    (d.cepOrigem = :cepOrigem and d.cepDestino = :cepDestino) 
    or 
    (d.cepOrigem = :cepDestino and d.cepDestino = :cepOrigem)
""")
    Distancia findAllByCepOrigemDestino(String cepOrigem, String cepDestino);
}
