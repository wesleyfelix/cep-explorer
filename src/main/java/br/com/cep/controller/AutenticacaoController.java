package br.com.cep.controller;

import br.com.cep.domain.usuario.DadosCdadstroUsuario;
import br.com.cep.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import br.com.cep.domain.usuario.DadosAutenticacao;
import br.com.cep.domain.usuario.Usuario;
import br.com.cep.infra.security.DadosTokenJWT;
import br.com.cep.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication =  manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }


    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCdadstroUsuario dados) {

        if(repository.findAllByLogin(dados.login()) == null){
            String login = dados.login();
            String senha = passwordEncoder.encode(dados.senha());
            Usuario usuario = new Usuario(null, login, senha);
            repository.save(usuario);
            return ResponseEntity.ok("cadastrado com sucesso");
        }else{
            return ResponseEntity.badRequest().body("Usuário já existe");
        }

    }
}
