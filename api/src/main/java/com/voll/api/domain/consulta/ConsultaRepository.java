package com.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface ConsultaRepository  extends JpaRepository<Consulta, Long> {



    boolean existsByPacienteIdAndFechaBetween(@NotNull Long aLong, LocalDateTime primerHorario, LocalDateTime ultimoHorario);

    boolean existsByMedicoIdAndFecha(Long idMedico, @NotNull @Future LocalDateTime fecha);
}
