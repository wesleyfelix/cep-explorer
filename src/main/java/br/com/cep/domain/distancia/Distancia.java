package br.com.cep.domain.distancia;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "distancia")
@Entity(name = "Distancia")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Distancia {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String cepOrigem;
    private String cepDestino;
    private String url;
    private String distanciaPe;
    private String distanciaCarro;
}
