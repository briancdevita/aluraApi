package com.voll.api.domain.consulta.validaciones;

import com.voll.api.domain.consulta.DatosReservaConsulta;
import com.voll.api.domain.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidacionMedicoActivo implements ValidadorDeConsultas {


    @Autowired
    private MedicoRepository medicoRepository;

    public void validar (DatosReservaConsulta datos) {
        if (datos.idMedico() == null) {
           return;
        }
        var medicoEstaActivo = medicoRepository.findActivoById(datos.idMedico());
        if (!medicoEstaActivo) {
            throw new com.voll.api.domain.ValidacionException("El médico no está activo, no se puede reservar la consulta");
        }
    }

}
