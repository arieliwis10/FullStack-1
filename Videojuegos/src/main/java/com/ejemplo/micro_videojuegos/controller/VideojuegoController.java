package com.ejemplo.micro_videojuegos.controller;

import com.ejemplo.micro_videojuegos.model.Videojuego;
import com.ejemplo.micro_videojuegos.model.ResponseWrapper;
import com.ejemplo.micro_videojuegos.service.VideojuegoService;

import jakarta.validation.Valid;
import lombok.extern.sld4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/videojuegos")
public class VideojuegoController {

    private final VideojuegoService videojuegoService;

    public VideojuegoController(VideojuegoService videojuegoService) {
        this.videojuegoService = videojuegoService;
    }

     /**
     * Obtiene todas las videojuego ordenadas por ID.
     */
    @GetMapping
    public ResponseEntity<?> obtenerTodas() {
        log.info("Get / videojuegos - Obteniendo todos los videojuegos");

        List<Videojuego> videojuego = videojuegoService.obtenerTodas();

        if (videojuego.isEmpty()) {
            log.warn("No hay videojuegos registrados actualmente");

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay videojuego registradas actualmente");
        }

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "OK",
                        videojuego.size(),
                        LocalDateTime.now(),
                        videojuego));
    }

    /**
     * Busca un videojuego por su ID. Si no la encuentra, lanza una excepción.
     */
    @GetMapping("/{id}")
    public Videojuego obtenerPorId(@PathVariable Long id) {
        log.info("Get /videojuegos/{} - Buscando videojuegos por id", id);
        return videojuegoService.obtenerPorId(id);
    }

    /**
     * 🟦 SESIÓN 5: Crea una videojuego solo si el ID no existe.
     * Si el ID ya está registrado, lanza un error 400.
     */
    @PostMapping
    public ResponseEntity<ResponseWrapper<Videojuego>> crearVideojuego(@Valid @RequestBody Videojuego videojuego) {
        log.info("POST /videojuegos - Creando videojuegos: {}", evento.getTitulo());
        Videojuego creada = videojuegoService.guardar(videojuego);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(
                        "Videojuego creada exitosamente",
                        1,
                        LocalDateTime.now(),
                        List.of(creada)));
    }

    /**
     * 🟦 SESIÓN 5: Actualiza los campos de una videojuego existente.
     * Lanza error si no existe.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Videojuego>> actualizarVideojuego(@PathVariable Long id,
            @Valid @RequestBody Videojuego videojuegoActualizada) {
        log.info("PUT /videojuegos - Actualizando videojuegos", id);

                Videojuego actualizada = videojuegoService.actualizar(id, videojuegoActualizada);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Videojuego actualizada exitosamente",
                        1,
                        LocalDateTime.now(),
                        List.of(actualizada)));
    }

    /**
     * 🟦 SESIÓN 5: Elimina una videojuego por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarVideojuego(@PathVariable Long id) {
        log.warn("Delete /videojuegos/{} - Eliminando videojuego", id);
        videojuegoService.eliminar(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Videojuego eliminada exitosamente",
                        0,
                        LocalDateTime.now(),
                        null));
    }
}