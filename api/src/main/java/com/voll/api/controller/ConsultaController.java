package com.voll.api.controller;


import com.voll.api.domain.consulta.DatosDetalleConsulta;
import com.voll.api.domain.consulta.DatosReservaConsulta;
import com.voll.api.domain.consulta.RerservaDeConsultas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {


    @Autowired
    private RerservaDeConsultas reservar;

    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid DatosReservaConsulta datos) {
        System.out.println("Datos de reserva de consulta: " + datos);
        var detallesConsulta = reservar.reservar(datos);
        return ResponseEntity.ok(detallesConsulta);
    }
}
