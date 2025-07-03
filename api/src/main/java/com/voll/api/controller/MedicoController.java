package com.voll.api.controller;



import com.voll.api.domain.medico.DatosActualizacionMedico;
import com.voll.api.domain.medico.DatosDetallesMedico;
import com.voll.api.domain.medico.DatosListaMedicos;
import com.voll.api.domain.medico.DatosRegistroMedico;
import com.voll.api.domain.medico.models.Medico;
import com.voll.api.domain.medico.repository.MedicoRepository;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")

public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar (@RequestBody @Valid DatosRegistroMedico datos, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(datos);
        medicoRepository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetallesMedico(medico));
    }


    @GetMapping
    public ResponseEntity<Page<DatosListaMedicos>> listar(@PageableDefault(size=10, sort = {"nombre"}) Pageable paginacion) {
        var page =  medicoRepository.findAllByActivoTrue(paginacion).map(DatosListaMedicos::new);
        return ResponseEntity.ok(page);
    }


    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionMedico datos){
        var medico = medicoRepository.getReferenceById(datos.id());
        medico.actualizarDatos(datos);
        return ResponseEntity.ok(new DatosDetallesMedico(medico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.eliminar();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetallesMedico> detalle(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetallesMedico(medico));
    }
}
