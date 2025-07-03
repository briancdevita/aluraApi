package com.voll.api.domain.medico.repository;

import com.voll.api.domain.medico.Especialidad;
import com.voll.api.domain.medico.models.Medico;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByActivoTrue(Pageable paginacion);


    @Query("""
    select m from Medico m
        where
        m.activo = true
        and
        m.especialidad = :especialidad
        and m.id not in (
            select c.medico.id from Consulta c
            where c.fecha = :fecha
        )
        order by rand()
        limit 1
    """)
    Medico elegirMedicoAleatorioSinAtenderHoy(Especialidad especialidad, @NotNull @Future LocalDateTime fecha);


    @Query("""
    select m.activo from Medico m
        where m.id = :idMedico
    """)
    boolean findActivoById(Long idMedico);
}
