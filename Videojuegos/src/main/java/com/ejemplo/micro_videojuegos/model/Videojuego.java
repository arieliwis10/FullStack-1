package com.ejemplo.micro_videojuegos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// -------------------- IMPORTACIONES -------------------------

// Librería de Lombok que genera automáticamente los métodos getters y setters
import lombok.AllArgsConstructor;

// Lombok genera automáticamente métodos como toString(), equals(), hashCode(), getters y setters
import lombok.Data;

// Lombok genera un constructor vacío sin parámetros
import lombok.NoArgsConstructor;

// 🟦 [SESION 5] Importaciones para validar automáticamente los campos
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;


@Data // Lombok: genera automáticamente todos los getters, setters, toString(),
      // equals(), hashCode()
@AllArgsConstructor // Lombok: genera un constructor con todos los atributos como parámetros
@NoArgsConstructor // Lombok: genera un constructor sin parámetros (vacío)
@Entity // 🔵 Indicamos que es una entidad de base de datos
@Table(name = "videojuegos") // 🔵 Mapeamos a la tabla "peliculas"
public class Videojuego {

    // ID
    @Id // 🔵 Indicamos que esta es la Primary Key
    // 🟦 [SESION 5] Validamos que el ID no sea nulo ni negativo
    @NotNull(message = "El ID no puede ser nulo")
    @Positive(message = "El ID debe ser un número positivo")
    private Long id;

    // Título del videojuego
    // 🟦 [SESION 5] Validamos que el título no esté vacío y tenga un tamaño razonable
    @NotBlank(message = "El título no puede estar vacío")
    @Size(min = 1, max = 100, message = "El título debe tener entre 1 y 100 caracteres")
    private String titulo;

    // Género del videojuego
    // 🟦 [SESION 5] Validamos que el género no esté vacío y tenga una longitud mínima
    @NotBlank(message = "El género no puede estar vacío")
    @Size(min = 3, max = 50, message = "El género debe tener entre 3 y 50 caracteres")
    private String genero;

    // Consola del videojuego
    // 🟦 [SESION 5] Validamos que la sinopsis no supere los 255 caracteres (opcional)
    @Size(max = 255, message = "La consola no debe superar los 255 caracteres")
    private String consola;

}