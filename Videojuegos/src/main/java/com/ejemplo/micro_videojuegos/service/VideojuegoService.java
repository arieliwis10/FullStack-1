package com.ejemplo.micro_videojuegos.service;

import com.ejemplo.micro_videojuegos.exception.VideojuegoNotFoundException;
import com.ejemplo.micro_videojuegos.model.Videojuego;
import com.ejemplo.micro_videojuegos.repository.VideojuegoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideojuegoService {

        @Autowired
        private VideojuegoRepository repo;

        
        public List<Videojuego> obtenerTodas() {
                return repo.findAll(Sort.by("id").ascending());
        }

        
        public Videojuego obtenerPorId(Long id) {
                return repo.findById(id)
                                .orElseThrow(() -> new VideojuegoNotFoundException(id));
        }

        
        public Videojuego guardar(Videojuego videojuego) {
                if (repo.existsById(videojuego.getId())) {
                        throw new IllegalArgumentException("Ya existe una película con ID " + videojuego.getId());
                }
                return repo.save(videojuego);
        }

        
        public Videojuego actualizar(Long id, Videojuego videojuegoActualizada) {
                Videojuego existente = repo.findById(id)
                                .orElseThrow(() -> new VideojuegoNotFoundException(id));

                existente.setTitulo(videojuegoActualizada.getTitulo());
                existente.setGenero(videojuegoActualizada.getGenero());
                existente.setConsola(videojuegoActualizada.getConsola());

                return repo.save(existente);
        }

        
        public void eliminar(Long id) {
                Videojuego existente = repo.findById(id)
                                .orElseThrow(() -> new VideojuegoNotFoundException(id));

                repo.delete(existente);
        }
}
