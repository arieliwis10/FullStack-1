package com.ejemplo.micro_mascotas.controller;

import com.ejemplo.micro_mascotas.model.Evento;
import com.ejemplo.micro_mascotas.model.ResponseWrapper;
import com.ejemplo.micro_mascotas.service.EventoService;
import com.ejemplo.micro_mascotas.hateoas.EventoModelAssembler;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Slf4j
@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;
    private final EventoModelAssembler assembler;

    // Constructor con inyección del servicio y del assembler
    public EventoController(EventoService eventoService, EventoModelAssembler assembler) {
        this.eventoService = eventoService;
        this.assembler = assembler;
    }

    // ✅ Obtener todos los eventos con modelo HATEOAS
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Evento>>> obtenerTodas() {
        log.info("GET /eventos - Obteniendo todos los eventos");

        List<Evento> eventos = eventoService.obtenerTodas();

        if (eventos.isEmpty()) {
            log.warn("No hay eventos registrados actualmente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CollectionModel.empty());
        }

        List<EntityModel<Evento>> modelos = eventos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(modelos,
                linkTo(methodOn(EventoController.class).obtenerTodas()).withSelfRel()));
    }

    // ✅ Obtener evento por ID con enlaces HATEOAS
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Evento>> obtenerPorId(@PathVariable Long id) {
        log.info("GET /eventos/{} - Buscando evento por ID", id);

        Evento evento = eventoService.obtenerPorId(id);

        return ResponseEntity.ok(assembler.toModel(evento));
    }

    // ✅ Crear nuevo evento
    @PostMapping
    public ResponseEntity<EntityModel<Evento>> crearEvento(@Valid @RequestBody Evento evento) {
        log.info("POST /eventos - Creando evento: {}", evento.getNombre());

        Evento creado = eventoService.guardar(evento);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assembler.toModel(creado));
    }

    // ✅ Actualizar evento
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Evento>> actualizarEvento(@PathVariable Long id,
            @Valid @RequestBody Evento eventoActualizado) {
        log.info("PUT /eventos/{} - Actualizando evento", id);

        Evento actualizado = eventoService.actualizar(id, eventoActualizado);

        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    // ✅ Eliminar evento
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarEvento(@PathVariable Long id) {
        log.warn("DELETE /eventos/{} - Eliminando evento", id);

        eventoService.eliminar(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Evento eliminado exitosamente",
                        0,
                        null));
    }
}