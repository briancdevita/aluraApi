package com.voll.api.domain.direccion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosDireccion(
        @NotBlank String calle,
        String numero,
        @NotBlank String distrito,
        @NotBlank String ciudad,
        @NotBlank String complemento
) {
}
