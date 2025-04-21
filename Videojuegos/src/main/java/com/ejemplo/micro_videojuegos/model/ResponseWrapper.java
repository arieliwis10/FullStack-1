package com.ejemplo.micro_videojuegos.model;

// ------------------------ IMPORTACIONES ------------------------

// Importamos una anotación que nos permite definir en qué orden queremos que aparezcan los atributos en el JSON
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonFormat;

// Importamos para poder usar fechas y horas en Java
import java.time.LocalDateTime;

// Importamos la clase List, necesaria para manejar listas de cualquier tipo, en este caso de Películas o cualquier otra entidad
import java.util.List;

// ------------------------ CLASE ------------------------

/**
 * Clase genérica que encapsula las respuestas REST del microservicio.
 * Su propósito es estructurar las respuestas que enviaremos al cliente de forma ordenada y profesional.
 *
 * Utilizamos Generics <T> para que este wrapper funcione con cualquier tipo de dato (películas, usuarios, etc.).
 */
@JsonPropertyOrder({ "status", "cantidad", "timestamp", "data" }) // Esto define explícitamente el orden de los atributos cuando se genere el JSON
public class ResponseWrapper<T> {

    // ------------------------ ATRIBUTOS ------------------------

    // Indica si la operación fue exitosa u otro estado (por ejemplo: OK, ERROR)
    private String status;

    // Cantidad de elementos devueltos en la lista de datos
    private int cantidad;

    // Fecha y hora exacta en la que se genera la respuesta
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    // Lista de datos que se incluye en la respuesta
    private List<T> data;

    // ------------------------ CONSTRUCTOR ------------------------

    public ResponseWrapper(String status, int cantidad, LocalDateTime timestamp, List<T> data) {
        this.status = status;
        this.cantidad = cantidad;
        this.timestamp = timestamp;
        this.data = data;
    }

    // ------------------------ GETTERS Y SETTERS ------------------------

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}