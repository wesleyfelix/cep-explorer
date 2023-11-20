package br.com.cep.controller;

import br.com.cep.domain.cep.CepResponse;
import br.com.cep.domain.cep.ConsultaCep;
import br.com.cep.domain.distancia.ConsultaDistancia;
import br.com.cep.domain.distancia.Distancia;
import br.com.cep.domain.distancia.DistanciaResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@RestController
@RequestMapping("cep")
public class CepController {

//    @Autowired
//    private MedicoRepository repository;

    @Autowired
    private ConsultaCep consultaCep;

    @Autowired
    private ConsultaDistancia consultaDistancia;

    @GetMapping("/{cepOrigem}/{cepDestino}")
    public ResponseEntity consultarDistancia(@PathVariable String cepOrigem, @PathVariable String cepDestino) throws IOException, InterruptedException {

        if(consultaCep.consultaCepOrigemDestino(cepOrigem,cepDestino)){
            Distancia dist = consultaDistancia.consultaDistanciaOrigemDestino(cepOrigem, cepDestino);
            return ResponseEntity.ok(new DistanciaResponse(dist));
        }else {
            return ResponseEntity.badRequest().body("deu ruim pae");
        }
    }

    @GetMapping("/{cep}")
    public ResponseEntity getEndereco(@PathVariable String cep){
        return ResponseEntity.ok(new CepResponse(consultaCep.getEndereco(cep)));
    }

}
