package com.voll.api.domain.medico;

import com.voll.api.domain.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionMedico(
        @NotNull  Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion
) {
}
