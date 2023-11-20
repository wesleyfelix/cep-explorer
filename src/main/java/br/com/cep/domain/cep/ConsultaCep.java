

package br.com.cep.domain.cep;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.MINUTES;

@Component
public class ConsultaCep {

    private static final Logger log = LoggerFactory.getLogger(ConsultaCep.class);



    private static final String viaCepUrl = "https://viacep.com.br/ws/";


    @Autowired
    CepRepository repository;

    private Boolean cepsValidos = false;
    public boolean consultaCepOrigemDestino(String cepOrigem, String cepDestino)  {

        if(repository.findAllByCep(formatarCEP(cepOrigem)) == null){
            try {
                HttpClient httpClient = HttpClient.newBuilder()
                        .connectTimeout(Duration.of(1, MINUTES))
                        .build();

                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(viaCepUrl+cepOrigem+"/json"))
                        .build();

                HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

                ObjectMapper objectMapper = new ObjectMapper();
                if(!httpResponse.body().contains("erro")){
                    Cep cepObject = objectMapper.readValue(httpResponse.body(), Cep.class);
                    repository.save(cepObject);
                    log.info("CEP Origem Salvo no banco: " + formatarCEP(cepOrigem));
                    cepsValidos = true;

                }else{
                    cepsValidos = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }

        }else{
            log.info("CEP Origem já existe no banco: " + formatarCEP(cepOrigem));
            cepsValidos = true;
        }

        if(repository.findAllByCep(formatarCEP(cepDestino)) == null){
            try {
                HttpClient httpClient = HttpClient.newBuilder()
                        .connectTimeout(Duration.of(1, MINUTES))
                        .build();

                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(viaCepUrl+cepDestino+"/json"))
                        .build();

                HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

                ObjectMapper objectMapper = new ObjectMapper();
                if(!httpResponse.body().contains("erro")){
                    Cep cepObject = objectMapper.readValue(httpResponse.body(), Cep.class);
                    repository.save(cepObject);
                    log.info("CEP Destino Salvo no banco: " + formatarCEP(cepDestino));
                    cepsValidos = true;
                }else{
                    cepsValidos = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }else{
            log.info("CEP Destino já existe no banco: " + formatarCEP(cepDestino));
            cepsValidos = true;
        }
        return cepsValidos;
    }

    public static String formatarCEP(String cep) {
        if (cep.length() == 8) {
            return cep.substring(0, 5) + "-" + cep.substring(5);
        } else {
            return cep;
        }
    }

    public static String incrementarStringNumerica(String input) {

        int numero = Integer.parseInt(input);
        numero++;
        return String.format("%08d", numero);
    }
}