package com.ejemplo.micro_mascotas.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; // Importa funciones para generar enlaces

import com.ejemplo.micro_mascotas.controller.EventoController;
import com.ejemplo.micro_mascotas.model.Evento;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * Esta clase convierte una entidad Evento en un EntityModel<Evento>
 * enriquecido con enlaces HATEOAS.
 * 
 * Implementa RepresentationModelAssembler, que es una interfaz de Spring
 * HATEOAS
 * para transformar objetos de dominio (Evento) en recursos RESTful
 * enriquecidos.
 */
@Component
public class EventoModelAssembler implements RepresentationModelAssembler<Evento, EntityModel<Evento>> {

    /**
     * Este método transforma una Evento en un EntityModel con enlaces.
     * Incluye:
     * ✅ self → Ver este evento
     * 🗑 delete → Eliminar el evento
     * 🔁 update → Actualizar el evento
     * 📋 all → Ver todas los eventos
     */
    @Override
    public EntityModel<Evento> toModel(Evento evento) {
        return EntityModel.of(
                evento, // Entidad original

                // Enlace al detalle de la película (GET /eventos/{id})
                linkTo(methodOn(EventoController.class)
                        .obtenerPorId(evento.getId()))
                        .withSelfRel(),

                // Enlace para eliminar (DELETE /eventos/{id})
                linkTo(methodOn(EventoController.class)
                        .eliminarEvento(evento.getId()))
                        .withRel("delete"),

                // Enlace para actualizar (PUT /evento/{id}) – cuerpo ignorado aquí
                linkTo(methodOn(EventoController.class)
                        .actualizarEvento(evento.getId(), null))
                        .withRel("update"),

                // Enlace para ver todas las eventos (GET /eventos)
                linkTo(methodOn(EventoController.class)
                        .obtenerTodas())
                        .withRel("all"));
    }
}
