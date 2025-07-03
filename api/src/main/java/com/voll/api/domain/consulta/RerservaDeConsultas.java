package com.voll.api.domain.consulta;


import com.voll.api.domain.ValidacionException;
import com.voll.api.domain.consulta.validaciones.ValidadorDeConsultas;
import com.voll.api.domain.medico.models.Medico;
import com.voll.api.domain.medico.repository.MedicoRepository;
import com.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RerservaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorDeConsultas> validadores;

    public DatosDetalleConsulta reservar(DatosReservaConsulta datos) {
        System.out.println("Received fecha: " + datos.fecha()); // Should show non-null
       if (!pacienteRepository.existsById(datos.idPaciente())) {
           throw  new ValidacionException("El id del paciente no existe");
       }
         if ( datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())) {
              throw  new ValidacionException("El id del medico no existe");
         }

         validadores.forEach(v -> v.validar(datos));

       var medico = elegirMedico(datos);
       if (medico == null) {
           throw new ValidacionException("No hay medicos disponibles para la fecha y especialidad seleccionada");
       }
       var paciente = pacienteRepository.findById(datos.idPaciente()).get();
       var consulta = new Consulta(null, medico, paciente, datos.fecha());
       consultaRepository.save(consulta);
       return new DatosDetalleConsulta(consulta);
    }

    private Medico elegirMedico(DatosReservaConsulta datos) {
        if (datos.idMedico() != null) {
            return medicoRepository.getReferenceById(datos.idMedico());
        } else if (datos.especialidad() != null) {
            return medicoRepository.elegirMedicoAleatorioSinAtenderHoy(datos.especialidad(), datos.fecha());
        } else {
            throw new ValidacionException("Debe ingresar el id del medico o la especialidad");
        }
    }

}
