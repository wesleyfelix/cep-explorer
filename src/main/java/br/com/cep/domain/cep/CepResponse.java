package br.com.cep.domain.cep;

public record CepResponse(String cep,
                          String logradouro,
                          String complemento,
                          String bairro,
                          String localidade,
                          String uf,
                          String ibge,
                          String gia,
                          String ddd,
                          String siafi) {
    public CepResponse(Cep cep){
        this(cep.getCep(), cep.getLogradouro(), cep.getComplemento(), cep.getBairro(), cep.getLocalidade(), cep.getUf(), cep.getIbge(),cep.getGia(), cep.getDdd(), cep.getSiafi());
    }
}
