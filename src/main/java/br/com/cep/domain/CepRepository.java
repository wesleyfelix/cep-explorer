package br.com.cep.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface CepRepository extends JpaRepository<Cep, Long> {
    Cep findAllByCep(String cep);
    @Query("""
    select c from Cep c ORDER BY c.cep DESC limit 1
""")
    Cep findAllByCepLast();

    @Transactional
    @Modifying
    @Query("DELETE FROM Cep c WHERE c.logradouro = :logradouro")
    void deleteByLogradouro(String logradouro);
}
