package com.ejemplo.micro_mascotas.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Evento {
     private Long id;
    private String nombre;
    private String fecha;
    private String lugar;
    private String tipo;
    private List<String> participantes;
}
