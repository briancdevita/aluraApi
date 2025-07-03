package com.voll.api.infra.exception;


import com.voll.api.domain.ValidacionException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GestorDeErrores {



    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity GestionarError404 () {
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity GestionarError400 (MethodArgumentNotValidException ex ) {
        var errores = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errores.stream().map(DatosErrorValidacion::new).toList());
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity tratarErrorDeValidacion (ValidacionException e ) {
       return ResponseEntity.badRequest().body(e.getMessage());
    }




    public record DatosErrorValidacion(String campo, String mensaje) {
        public DatosErrorValidacion (FieldError error) {
            this(error.getField(), error.getDefaultMessage());

        }
    }


}
