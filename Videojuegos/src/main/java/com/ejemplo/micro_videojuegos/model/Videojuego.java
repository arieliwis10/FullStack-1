package com.ejemplo.micro_videojuegos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Videojuego {
    private Long id;
    private String titulo;
    private String genero;
    private String consola;
}
