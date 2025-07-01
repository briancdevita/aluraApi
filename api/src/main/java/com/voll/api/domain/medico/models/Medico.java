package com.voll.api.domain.medico.models;


import com.voll.api.domain.direccion.models.Direccion;
import com.voll.api.domain.medico.DatosActualizacionMedico;
import com.voll.api.domain.medico.DatosRegistroMedico;
import com.voll.api.domain.medico.Especialidad;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Table(name = "medicos")
@Entity
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Medico {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private boolean activo;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;



    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico datos) {
        this.id = null;
        this.activo = true;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.documento = datos.documento();
        this.especialidad = datos.especialidad();
        this.direccion = new Direccion(datos.direccion());
    }

    public Medico() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void actualizarDatos(@Valid DatosActualizacionMedico datos) {
        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
        }
        if (datos.telefono() != null) {
            this.telefono = datos.telefono();
        }
        if (datos.direccion() != null) {
            this.direccion.actualizarDatosDireccion(datos.direccion());
        }
    }

    public void eliminar() {
        this.activo = false;
    }
}
