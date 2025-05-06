package com.ejemplo.micro_videojuegos.service;

import com.ejemplo.micro_videojuegos.exception.VideojuegoNotFoundException;
import com.ejemplo.micro_videojuegos.model.Videojuego;
import com.ejemplo.micro_videojuegos.repository.VideojuegoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
public class VideojuegoService {

    private final VideojuegoRepository repo;

    public VideojuegoService(VideojuegoRepository repo) {
        this.repo = repo;
    }

    public List<Videojuego> obtenerTodas() {
        log.debug("Servicio: ObtenerTodas()");
        return repo.findAll(Sort.by("id").ascending());
    }

    /**
     * Obtiene un videojuego por su ID o lanza una excepción si no existe.
     */
    public Videojuego obtenerPorId(Long id) {
        log.debug("Servicio: ObtenerPorId()");
        return repo.findById(id)
                .orElseThrow(() -> new VideojuegoNotFoundException(id));
    }

    /**
     * Guarda un nuevo videojuego si no existe uno con el mismo ID.
     */
    public Videojuego guardar(Videojuego videojuego) {
        log.debug("Servicio: Guardar()");
        if (repo.existsById(videojuego.getId())) {
            throw new IllegalArgumentException("Ya existe un videojuego con ID " + videojuego.getId());
        }
        return repo.save(videojuego);
    }

    /**
     * Actualiza un videojuego existente.
     */
    public Videojuego actualizar(Long id, Videojuego videojuegoActualizado) {
        log.debug("Servicio: Actualizar()");
        Videojuego existente = repo.findById(id)
                .orElseThrow(() -> new VideojuegoNotFoundException(id));

        existente.setTitulo(videojuegoActualizado.getTitulo());
        existente.setGenero(videojuegoActualizado.getGenero());
        existente.setConsola(videojuegoActualizado.getConsola());

        return repo.save(existente);
    }

    /**
     * Elimina un videojuego por su ID.
     */
    public void eliminar(Long id) {
        log.debug("Servicio: Eliminar()");
        Videojuego existente = repo.findById(id)
                .orElseThrow(() -> new VideojuegoNotFoundException(id));

        repo.delete(existente);
    }
}
