//
//
//package br.com.cep.domain.cep;
//
//import java.io.IOException;
////import java.text.SimpleDateFormat;
////import java.util.Date;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
////import com.google.gson.Gson;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
////import java.io.IOException;
//import java.net.URI;
////import java.net.http.HttpClient;
////import java.net.http.HttpRequest;
////import java.net.http.HttpResponse;
//import java.time.Duration;
//import static java.time.temporal.ChronoUnit.MINUTES;
//
//@Component
//public class ConsultaCepSchedule {
//
//    private static final Logger log = LoggerFactory.getLogger(ConsultaCepSchedule.class);
//
////    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//
//    private static final String viaCepUrl = "https://viacep.com.br/ws/";
////    private static final Gson gson = new Gson();
//
//    @Autowired
//    CepRepository repository;
//
////    @Scheduled(fixedRate = 1800)
//    public void consultaCep() {
//
//        String cep = "";
//        if(repository.findAllByCepLast() == null) {
//            cep = "01001000";
//        }else{
//            Cep verificaUltimoCep = repository.findAllByCepLast();
//            cep = incrementarStringNumerica(verificaUltimoCep.getCep().replace("-", ""));
//        }
//
//
//        try {
//            HttpClient httpClient = HttpClient.newBuilder()
//                    .connectTimeout(Duration.of(1, MINUTES))
//                    .build();
//
//            HttpRequest httpRequest = HttpRequest.newBuilder()
//                    .GET()
//                    .uri(URI.create(viaCepUrl+cep+"/json"))
//                    .build();
//
//            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            if(!httpResponse.body().contains("erro")){
//                Cep cepObject = objectMapper.readValue(httpResponse.body(), Cep.class);
//
//                if(repository.findAllByCep(formatarCEP(cep)) == null){
//                    repository.save(cepObject);
//                    log.info("CEP Salvo no banco: " + formatarCEP(cep));
//                }else{
//                    log.info("CEP " + cep + " já existe no banco");
//                }
//            }else{
//                repository.deleteByLogradouro("ERRO NA CONSULTA");
//                log.error("Erro na consulta do CEP: " + formatarCEP(cep));
//                Cep cepErro = new Cep(null, formatarCEP(cep), "ERRO NA CONSULTA", null, null, null, null, null, null, null, null);
//                repository.save(cepErro);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e.getMessage());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    public static String formatarCEP(String cep) {
//        if (cep.length() == 8) {
//            return cep.substring(0, 5) + "-" + cep.substring(5);
//        } else {
//            return cep;
//        }
//    }
//
//    public static String incrementarStringNumerica(String input) {
//
//        int numero = Integer.parseInt(input);
//        numero++;
//        return String.format("%08d", numero);
//    }
//}