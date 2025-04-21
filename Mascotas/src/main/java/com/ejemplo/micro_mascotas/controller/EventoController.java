package com.ejemplo.micro_mascotas.controller;

import com.ejemplo.micro_mascotas.model.Evento;
import com.ejemplo.micro_mascotas.model.ResponseWrapper;
import com.ejemplo.micro_mascotas.service.EventoService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    /**
     * Obtiene todos los eventos registrados.
     */
    @GetMapping
    public ResponseEntity<?> obtenerTodas() {
        List<Evento> eventos = eventoService.obtenerTodas();

        if (eventos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay eventos registrados actualmente");
        }

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "OK",
                        eventos.size(),
                        eventos));
    }

    /**
     * Obtiene un evento por su ID.
     */
    @GetMapping("/{id}")
    public Evento obtenerPorId(@PathVariable Long id) {
        return eventoService.obtenerPorId(id); // ya lanza la excepción si no existe
    }

    /**
     * Obtiene los participantes de un evento por ID.
     */
    @GetMapping("/{id}/participantes")
    public ResponseEntity<?> obtenerParticipantes(@PathVariable Long id) {
        Evento evento = eventoService.obtenerPorId(id); // ya lanza la excepción si no existe
        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Participantes del evento",
                        evento.getParticipantes().size(),
                        evento.getParticipantes()));
}

    /**
     * Crea un nuevo evento.
     */
    @PostMapping
    public ResponseEntity<ResponseWrapper<Evento>> crearEvento(@Valid @RequestBody Evento evento) {
        Evento creado = eventoService.guardar(evento);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(
                        "Evento creado exitosamente",
                        1,
                        List.of(creado)));
    }

    /**
     * Actualiza un evento por ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Evento>> actualizarEvento(@PathVariable Long id,
            @Valid @RequestBody Evento eventoActualizado) {
        Evento actualizado = eventoService.actualizar(id, eventoActualizado);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Evento actualizado exitosamente",
                        1,
                        List.of(actualizado)));
    }

    /**
     * Elimina un evento por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarEvento(@PathVariable Long id) {
        eventoService.eliminar(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Evento eliminado exitosamente",
                        0,
                        null));
    }
}