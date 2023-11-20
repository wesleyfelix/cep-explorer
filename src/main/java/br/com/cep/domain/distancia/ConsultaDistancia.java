

package br.com.cep.domain.distancia;

import br.com.cep.domain.browser.BrowserSetup;
import br.com.cep.domain.cep.Cep;
import br.com.cep.domain.cep.CepRepository;
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
public class ConsultaDistancia {

    private static final Logger log = LoggerFactory.getLogger(ConsultaDistancia.class);



    private static final String viaCepUrl = "https://viacep.com.br/ws/";


    @Autowired
    CepRepository cepRepository;

    @Autowired
    DistanciaRepository distanciaRepository;

    public Distancia consultaDistanciaOrigemDestino(String cepOrigem, String cepDestino) throws IOException, InterruptedException {

        if(distanciaRepository.findAllByCepOrigemDestino(formatarCEP(cepOrigem), formatarCEP(cepDestino)) == null){
            log.info("Inicinando consulta da distancia entre os CEPs");
            Cep origem = cepRepository.findAllByCep(formatarCEP(cepOrigem));
            Cep destino = cepRepository.findAllByCep(formatarCEP(cepDestino));
            String urlGoogle = "https://www.google.com/maps/dir/";
            String urlConsulta  = urlGoogle + origem.getLogradouro() + "+" + origem.getLocalidade() + "+" + origem.getUf() + "/" +
                    destino.getLogradouro() + "+" + destino.getLocalidade() + "+" + destino.getUf();

            BrowserSetup browserSetup = new BrowserSetup();
            ConsultaDistanciaBrowser calculaDistancia = new ConsultaDistanciaBrowser(browserSetup.getDriver());
            String getDistancia = calculaDistancia.getDistancia(urlConsulta);
            browserSetup.tearDown();
            log.info("Finalizou consulta da distancia entre os CEPs");
            Distancia distancia = new Distancia(null, formatarCEP(cepOrigem), formatarCEP(cepDestino), urlConsulta, getDistancia);

            distanciaRepository.save(distancia);
            return distancia;
        }else{
            log.info("Distância já calculada na base.");
            return distanciaRepository.findAllByCepOrigemDestino(formatarCEP(cepOrigem), formatarCEP(cepDestino));
        }
    }

    public static String formatarCEP(String cep) {
        if (cep.length() == 8) {
            return cep.substring(0, 5) + "-" + cep.substring(5);
        } else {
            return cep;
        }
    }
}