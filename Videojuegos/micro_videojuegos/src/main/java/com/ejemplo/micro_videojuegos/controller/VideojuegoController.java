package com.ejemplo.micro_videojuegos.controller;

import com.ejemplo.micro_videojuegos.model.Videojuego;
import com.ejemplo.micro_videojuegos.service.VideojuegoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/videojuegos")
public class VideojuegoController {

    private final VideojuegoService videojuegoService;

    public VideojuegoController(VideojuegoService videojuegoService) {
        this.videojuegoService = videojuegoService;
    }

    @GetMapping
    public ResponseEntity<List<Videojuego>> obtenerTodos() {
        return ResponseEntity.ok(videojuegoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Optional<Videojuego> videojuego = videojuegoService.obtenerPorId(id);
        if (videojuego.isPresent()) {
            return ResponseEntity.ok(videojuego.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "Videojuego con ID " + id + " no encontrado"));
        }
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<?> obtenerPorGenero(@PathVariable String genero) {
        List<Videojuego> resultado = videojuegoService.obtenerPorGenero(genero);
        if (!resultado.isEmpty()) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "No se encontraron videojuegos del género: " + genero));
        }
    }

    @GetMapping("/consola/{consola}")
    public ResponseEntity<?> obtenerPorConsola(@PathVariable String consola) {
        List<Videojuego> resultado = videojuegoService.obtenerPorConsola(consola);
        if (!resultado.isEmpty()) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "No se encontraron videojuegos para la consola: " + consola));
        }
    }
}