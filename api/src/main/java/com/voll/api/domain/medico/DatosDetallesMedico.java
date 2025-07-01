package com.voll.api.domain.medico;

import com.voll.api.domain.direccion.models.Direccion;
import com.voll.api.domain.medico.models.Medico;

public record DatosDetallesMedico(
        Long id,
        String nombre,
        String email,
        String documento,
        String telefono,
        Especialidad especialidad,
        Direccion direccion
) {
    public DatosDetallesMedico(Medico medico) {
        this(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getDocumento(),
                medico.getTelefono(),
                medico.getEspecialidad(),
                medico.getDireccion()
        );
    }
}
