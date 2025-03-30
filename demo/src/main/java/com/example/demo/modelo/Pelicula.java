package com.example.demo.modelo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Pelicula {
    private Long id;
    private String titulo;
    private int año;
    private String Director;
    private String genero;
    private String sinopsis;

}
