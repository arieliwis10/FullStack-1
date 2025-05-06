package com.ejemplo.micro_videojuegos.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; // Importa funciones para generar enlaces

import com.ejemplo.micro_videojuegos.controller.VideojuegoController;
import com.ejemplo.micro_videojuegos.model.Videojuego;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * Esta clase convierte una entidad Videojuego en un EntityModel<Videojuego>
 * enriquecido con enlaces HATEOAS.
 * 
 * Implementa RepresentationModelAssembler, que es una interfaz de Spring
 * HATEOAS
 * para transformar objetos de dominio (Videojuego) en recursos RESTful
 * enriquecidos.
 */
@Component
public class VideojuegoModelAssembler implements RepresentationModelAssembler<Videojuego, EntityModel<Videojuego>> {

    /**
     * Este método transforma un Videojuego en un EntityModel con enlaces.
     * Incluye:
     * ✅ self → Ver este videojuego
     * 🗑 delete → Eliminar el videojuego
     * 🔁 update → Actualizar el videojuego
     * 📋 all → Ver todos los videojuegos
     */
    @Override
    public EntityModel<Videojuego> toModel(Videojuego videojuego) {
        return EntityModel.of(
                videojuego, // Entidad original

                // Enlace al detalle del videojuego (GET /videojuegos/{id})
                linkTo(methodOn(VideojuegoController.class)
                        .obtenerPorId(videojuego.getId()))
                        .withSelfRel(),

                // Enlace para eliminar (DELETE /videojuegos/{id})
                linkTo(methodOn(VideojuegoController.class)
                        .eliminarVideojuego(videojuego.getId()))
                        .withRel("delete"),

                // Enlace para actualizar (PUT /videojuegos/{id}) – cuerpo ignorado aquí
                linkTo(methodOn(VideojuegoController.class)
                        .actualizarVideojuego(videojuego.getId(), null))
                        .withRel("update"),

                // Enlace para ver todos los videojuegos (GET /videojuegos)
                linkTo(methodOn(VideojuegoController.class)
                        .obtenerTodas())
                        .withRel("all"));
    }
}
