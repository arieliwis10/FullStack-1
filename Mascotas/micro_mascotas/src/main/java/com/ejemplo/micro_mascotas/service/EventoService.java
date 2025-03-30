package com.ejemplo.micro_mascotas.service;

import com.ejemplo.micro_mascotas.model.Evento;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    private final List<Evento> eventos = new ArrayList<>();

    public EventoService() {
        eventos.add(new Evento(1L, "Feria Canina", "10-04-2025", "Parque O'Higgins", "Feria", new ArrayList<>(List.of("Firulais", "Luna"))));
        eventos.add(new Evento(2L, "Competencia de Agilidad", "15-04-2025", "Cerro San Cristóbal", "Competencia", new ArrayList<>(List.of("Rocky", "Canela"))));
        eventos.add(new Evento(3L, "Desfile de Mascotas", "20-04-2025", "Parque Bicentenario", "Desfile", new ArrayList<>(List.of("Copito"))));
    }

    public List<Evento> obtenerTodas() {
        return eventos;
    }

    public Optional<Evento> obtenerPorId(Long id) {
        return eventos.stream().filter(e -> e.getId().equals(id)).findFirst();
    }
}