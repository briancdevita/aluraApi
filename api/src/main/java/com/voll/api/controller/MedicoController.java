package com.voll.api.controller;



import com.voll.api.medico.DatosActualizacionMedico;
import com.voll.api.medico.DatosListaMedicos;
import com.voll.api.medico.DatosRegistroMedico;
import com.voll.api.medico.models.Medico;
import com.voll.api.medico.repository.MedicoRepository;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional
    @PostMapping
    public void registrar (@RequestBody @Valid DatosRegistroMedico datos) {
        medicoRepository.save(new Medico(datos));
    }


    @GetMapping
    public Page<DatosListaMedicos> listar(@PageableDefault(size=10, sort = {"nombre"}) Pageable paginacion) {
        return medicoRepository.findAllByActivoTrue(paginacion).map(DatosListaMedicos::new);
    }


    @Transactional
    @PutMapping
    public void actualizar(@RequestBody @Valid DatosActualizacionMedico datos){
        var medico = medicoRepository.getReferenceById(datos.id());
        medico.actualizarDatos(datos);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.eliminar();
    }
}
