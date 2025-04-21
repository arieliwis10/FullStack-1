package com.ejemplo.micro_mascotas.repository;

// -------------------- IMPORTACIONES -------------------------

// Importamos JPA Repository para que Spring genere el acceso a base de datos
import org.springframework.data.jpa.repository.JpaRepository;

// Importamos la entidad Pelicula
import com.ejemplo.micro_mascotas.model.Evento;

// -------------------- INTERFAZ -------------------------

/**
 * Repositorio que permite realizar operaciones CRUD sobre la entidad Pelicula.
 * 
 * Al heredar de JpaRepository automáticamente Spring creará:
 * - findAll()
 * - findById()
 * - save()
 * - delete()
 * - y otros métodos útiles
 * 
 * Este repositorio conecta directamente con la tabla "peliculas" de Oracle.
 */

public interface EventoRepository extends JpaRepository<Evento, Long> {
    // Por ahora no necesitamos agregar métodos adicionales.
    // Solo con la herencia ya tenemos disponibles todas las operaciones básicas.
}
