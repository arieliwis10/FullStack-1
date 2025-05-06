package com.ejemplo.micro_videojuegos.controller;

import com.ejemplo.micro_videojuegos.model.Videojuego;
import com.ejemplo.micro_videojuegos.model.ResponseWrapper;
import com.ejemplo.micro_videojuegos.service.VideojuegoService;
import com.ejemplo.micro_videojuegos.hateoas.VideojuegoModelAssembler;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Slf4j
@RestController
@RequestMapping("/videojuegos")
public class VideojuegoController {

    private final VideojuegoService videojuegoService;
    private final VideojuegoModelAssembler assembler;

    // Constructor con inyección del servicio y del assembler
    public VideojuegoController(VideojuegoService videojuegoService, VideojuegoModelAssembler assembler) {
        this.videojuegoService = videojuegoService;
        this.assembler = assembler;
    }

    // ✅ Obtener todos los videojuegos con modelo HATEOAS
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Videojuego>>> obtenerTodas() {
        log.info("GET /videojuegos - Obteniendo todos los videojuegos");

        List<Videojuego> videojuegos = videojuegoService.obtenerTodas();

        if (videojuegos.isEmpty()) {
            log.warn("No hay videojuegos registrados actualmente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CollectionModel.empty());
        }

        List<EntityModel<Videojuego>> modelos = videojuegos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(modelos,
                linkTo(methodOn(VideojuegoController.class).obtenerTodas()).withSelfRel()));
    }

    // ✅ Obtener videojuego por ID con enlaces HATEOAS
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Videojuego>> obtenerPorId(@PathVariable Long id) {
        log.info("GET /videojuegos/{} - Buscando videojuego por ID", id);

        Videojuego videojuego = videojuegoService.obtenerPorId(id);

        return ResponseEntity.ok(assembler.toModel(videojuego));
    }

    // ✅ Crear nuevo videojuego
    @PostMapping
    public ResponseEntity<EntityModel<Videojuego>> crearVideojuego(@Valid @RequestBody Videojuego videojuego) {
        log.info("POST /videojuegos - Creando videojuego: {}", videojuego.getTitulo());

        Videojuego creado = videojuegoService.guardar(videojuego);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assembler.toModel(creado));
    }

    // ✅ Actualizar videojuego
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Videojuego>> actualizarVideojuego(@PathVariable Long id,
            @Valid @RequestBody Videojuego videojuegoActualizado) {
        log.info("PUT /videojuegos/{} - Actualizando videojuego", id);

        Videojuego actualizado = videojuegoService.actualizar(id, videojuegoActualizado);

        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    // ✅ Eliminar videojuego
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarVideojuego(@PathVariable Long id) {
        log.warn("DELETE /videojuegos/{} - Eliminando videojuego", id);

        videojuegoService.eliminar(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Videojuego eliminado exitosamente",
                        0,
                        LocalDateTime.now(), 
                        null));
    }
}