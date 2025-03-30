package com.example.demo.controller;

import com.example.demo.modelo.Pelicula;
import com.example.demo.service.PeliculaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService){
        this.peliculaService = peliculaService;
    
    }
    
    @GetMapping
    public List<Pelicula> obtenerTodas(){
        return peliculaService.obtenerTodas();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id){
    Optional<Pelicula> pelicula = peliculaService.obtenerPorId(id);

    if (pelicula.isPresent()) {
        return ResponseEntity.ok(pelicula.get());
    } else {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(Map.of("mensaje", "Película no encontrada"));
    }
}
    
    

}
