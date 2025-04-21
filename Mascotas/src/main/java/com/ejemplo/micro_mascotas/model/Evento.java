package com.ejemplo.micro_mascotas.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.FutureOrPresent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Clase que representa un Evento en el sistema.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @NotNull(message = "El ID no puede ser nulo")
    private Long id;

    @NotBlank(message = "El nombre del evento no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    // Si vas a manejar fechas con validación, te sugiero usar LocalDate o LocalDateTime en lugar de String
    @NotBlank(message = "La fecha no puede estar vacía")
    private String fecha;

    @NotBlank(message = "El lugar no puede estar vacío")
    @Size(min = 3, max = 100, message = "El lugar debe tener entre 3 y 100 caracteres")
    private String lugar;

    @NotBlank(message = "El tipo de evento no puede estar vacío")
    @Size(min = 3, max = 50, message = "El tipo debe tener entre 3 y 50 caracteres")
    private String tipo;

    @ElementCollection // Indica que es una colección embebida (en otra tabla relacionada)
    private List<String> participantes;
}