package com.voll.api.domain.consulta.validaciones;

import com.voll.api.domain.consulta.ConsultaRepository;
import com.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidacionPacienteSinOtraConsultaEnMismoDia  implements ValidadorDeConsultas {



    @Autowired
    private ConsultaRepository consultaRepository;


    public void validar (DatosReservaConsulta datos ) {
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);

        var pacienteTieneOtraConsultaEnElDia = consultaRepository.existsByPacienteIdAndFechaBetween(
                datos.idPaciente(),
                primerHorario,
                ultimoHorario
        );

        if (pacienteTieneOtraConsultaEnElDia) {
            throw new com.voll.api.domain.ValidacionException("El paciente ya tiene una consulta agendada para el día seleccionado");
        }

    }

}
