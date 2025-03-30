package com.ejemplo.micro_videojuegos.service;

import com.ejemplo.micro_videojuegos.model.Videojuego;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VideojuegoService {

    private final List<Videojuego> videojuegos = new ArrayList<>();

    public VideojuegoService() {
        videojuegos.add(new Videojuego(1L, "God of War", "Aventura", "PS5"));
        videojuegos.add(new Videojuego(2L, "Halo Infinite", "Shooter", "Xbox"));
        videojuegos.add(new Videojuego(3L, "Minecraft", "Creativo", "PC"));
    }

    public List<Videojuego> obtenerTodos() {
        return videojuegos;
    }

    public Optional<Videojuego> obtenerPorId(Long id) {
        return videojuegos.stream().filter(v -> v.getId().equals(id)).findFirst();
    }

    public List<Videojuego> obtenerPorGenero(String genero) {
        return videojuegos.stream()
                .filter(v -> v.getGenero().equalsIgnoreCase(genero))
                .toList();
    }

    public List<Videojuego> obtenerPorConsola(String consola) {
        return videojuegos.stream()
                .filter(v -> v.getConsola().equalsIgnoreCase(consola))
                .toList();
    }
}
