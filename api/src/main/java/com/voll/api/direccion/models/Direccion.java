package com.voll.api.direccion.models;


import com.voll.api.direccion.DatosDireccion;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Embeddable
@Getter

@AllArgsConstructor
public class Direccion {
    private String calle;
    private String numero;
    private String complemento;
    private String barrio;
    private String codigo_postal;
    private String ciudad;
    private String estado;


    public Direccion(DatosDireccion datosDireccion) {
        this.calle = datosDireccion.calle();
        this.numero = datosDireccion.numero();
        this.complemento = datosDireccion.complemento();
        this.barrio = datosDireccion.barrio();
        this.ciudad = datosDireccion.ciudad();
        this.codigo_postal = datosDireccion.codigo_postal();
        this.estado = datosDireccion.estado();
    }


    public Direccion() {
    }

    public void actualizarDatosDireccion(DatosDireccion direccion) {
        if (direccion.calle() != null) {
            this.calle = direccion.calle();
        }
        if (direccion.numero() != null) {
            this.numero = direccion.numero();
        }
        if (direccion.complemento() != null) {
            this.complemento = direccion.complemento();
        }
        if (direccion.barrio() != null) {
            this.barrio = direccion.barrio();
        }
        if (direccion.codigo_postal() != null) {
            this.codigo_postal = direccion.codigo_postal();
        }
        if (direccion.ciudad() != null) {
            this.ciudad = direccion.ciudad();
        }
        if (direccion.estado() != null) {
            this.estado = direccion.estado();
        }
    }
}
