package com.ejemplo.micro_mascotas.service;

import com.ejemplo.micro_mascotas.exception.EventoNotFoundException;
import com.ejemplo.micro_mascotas.model.Evento;
import com.ejemplo.micro_mascotas.repository.EventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
public class EventoService {

    private final EventoRepository repo;

    public EventoService(EventoRepository repo) {
        this.repo = repo;
    }

    public List<Evento> obtenerTodas() {

        log.debug("Servicio: ObtenerTodas()");
        return repo.findAll(Sort.by("id").ascending());
    }

    /**
     * Obtiene un evento por su ID o lanza una excepción si no existe.
     */
    public Evento obtenerPorId(Long id) {
        log.debug("Servicio: ObtenerPorId()");
        return repo.findById(id)
                .orElseThrow(() -> new EventoNotFoundException(id));
    }

    /**
     * Guarda un nuevo evento si no existe uno con el mismo ID.
     */
    public Evento guardar(Evento evento) {
        log.debug("Servicio: Guardar()");
        if (repo.existsById(evento.getId())) {
            throw new IllegalArgumentException("Ya existe un evento con ID " + evento.getId());
        }
        return repo.save(evento);
    }

    /**
     * Actualiza un evento existente.
     */
    public Evento actualizar(Long id, Evento eventoActualizado) {
        log.debug("Servicio: Actualizar()");
        Evento existente = repo.findById(id)
                .orElseThrow(() -> new EventoNotFoundException(id));

        existente.setNombre(eventoActualizado.getNombre());
        existente.setFecha(eventoActualizado.getFecha());
        existente.setLugar(eventoActualizado.getLugar());
        existente.setTipo(eventoActualizado.getTipo());
        existente.setParticipantes(eventoActualizado.getParticipantes());

        return repo.save(existente);
    }

    /**
     * Elimina un evento por su ID.
     */
    public void eliminar(Long id) {
        log.debug("Servicio: Eliminar ()");
        Evento existente = repo.findById(id)
                .orElseThrow(() -> new EventoNotFoundException(id));

        repo.delete(existente);
    }
}