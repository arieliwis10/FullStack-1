package com.ejemplo.micro_videojuegos.exception;

public class VideojuegoNotFoundException extends RuntimeException {
    public VideojuegoNotFoundException(Long id) {
        super("No se encontró el videojuego con ID: " + id);
    }
}