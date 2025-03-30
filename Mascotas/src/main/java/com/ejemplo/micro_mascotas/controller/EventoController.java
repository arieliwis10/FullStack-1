package com.ejemplo.micro_mascotas.controller;

import com.ejemplo.micro_mascotas.model.Evento;
import com.ejemplo.micro_mascotas.service.EventoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public ResponseEntity<List<Evento>> obtenerTodas() {
        return ResponseEntity.ok(eventoService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Optional<Evento> evento = eventoService.obtenerPorId(id);
        if (evento.isPresent()) {
            return ResponseEntity.ok(evento.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "Evento con ID " + id + " no encontrado"));
        }
    }

    @GetMapping("/{id}/participantes")
    public ResponseEntity<?> getParticipantes(@PathVariable Long id) {
        Optional<Evento> evento = eventoService.obtenerPorId(id);
        if (evento.isPresent()) {
            return ResponseEntity.ok(evento.get().getParticipantes());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "No se encontraron participantes porque el evento no existe"));
        }
    }
}
