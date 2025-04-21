// Indica que esta clase pertenece al paquete repository
package com.ejemplo.micro_videojuegos.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.micro_videojuegos.model.Videojuego;



public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {
}
