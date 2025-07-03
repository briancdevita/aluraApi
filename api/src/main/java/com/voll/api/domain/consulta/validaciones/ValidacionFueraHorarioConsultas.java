package com.voll.api.domain.consulta.validaciones;


import com.voll.api.domain.ValidacionException;
import com.voll.api.domain.consulta.DatosReservaConsulta;

public class ValidacionFueraHorarioConsultas implements ValidadorDeConsultas {

    public void validar(DatosReservaConsulta datos) {
        var fecha = datos.fecha();
        if (fecha.getHour() < 7 || fecha.getHour() > 18) {
            throw new ValidacionException("La consulta debe estar entre las 7:00 y las 19:00 horas");
        }
        if (fecha.getDayOfWeek().getValue() == 7) {
            throw new ValidacionException("No se pueden reservar consultas los domingos");
        }
    }
}
