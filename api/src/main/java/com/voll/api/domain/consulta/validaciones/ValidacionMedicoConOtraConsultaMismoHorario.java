package com.voll.api.domain.consulta.validaciones;

import com.voll.api.domain.ValidacionException;
import com.voll.api.domain.consulta.ConsultaRepository;
import com.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidacionMedicoConOtraConsultaMismoHorario implements ValidadorDeConsultas {


    @Autowired
    private ConsultaRepository consultaRepository;



    public void validar (DatosReservaConsulta datos){
        var medicoConOtraConsultaMismoHorario = consultaRepository.existsByMedicoIdAndFecha(
                datos.idMedico(), datos.fecha()
        );
        if (medicoConOtraConsultaMismoHorario) {
            throw new ValidacionException("El médico ya tiene una consulta reservada en ese horario");
        }
    }
}
