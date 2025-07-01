package com.voll.api.domain.paciente;

public record DatosListarPaciente(Long id, String nombre, String email, String documento) {

    public DatosListarPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumento());
    }
}
