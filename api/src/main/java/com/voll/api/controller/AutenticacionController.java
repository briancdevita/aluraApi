package com.voll.api.controller;


import com.voll.api.domain.usuario.DatosAutenticacion;
import com.voll.api.domain.usuario.Usuario;
import com.voll.api.infra.exception.security.DatosTokenJwt;
import com.voll.api.infra.exception.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/autenticacion")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos) {
        var autenticacionToken = new  UsernamePasswordAuthenticationToken(datos.login(), datos.contrasena());
        var autenticacion = manager.authenticate(autenticacionToken);

        var token  = tokenService.generateToken((Usuario) autenticacion.getPrincipal());

        return ResponseEntity.ok(new DatosTokenJwt(token));

    }
}
