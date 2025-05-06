package com.ejemplo.micro_videojuegos;

import com.ejemplo.micro_videojuegos.exception.VideojuegoNotFoundException;
import com.ejemplo.micro_videojuegos.model.Videojuego;
import com.ejemplo.micro_videojuegos.repository.VideojuegoRepository;
import com.ejemplo.micro_videojuegos.service.VideojuegoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.data.domain.Sort;

public class VideojuegoServiceTest {

    private VideojuegoRepository videojuegoRepository;
    private VideojuegoService videojuegoService;

    @BeforeEach
    public void setUp() {
        videojuegoRepository = mock(VideojuegoRepository.class);
        videojuegoService = new VideojuegoService(videojuegoRepository);
    }

    @Test
public void testObtenerTodas() {
    Videojuego v1 = new Videojuego(1L, "Zelda",  "Aventura", "Nintendo Switch");
    Videojuego v2 = new Videojuego(2L, "Mario Kart",  "Carreras", "Nintendo Switch");

    when(videojuegoRepository.findAll(Sort.by("id").ascending())).thenReturn(Arrays.asList(v1, v2));

    List<Videojuego> resultado = videojuegoService.obtenerTodas();

    assertEquals(2, resultado.size());
    assertEquals("Zelda", resultado.get(0).getTitulo());
}

@Test
public void testObtenerPorId_existente() {
    Videojuego videojuego = new Videojuego(1L, "Halo",  "Shooter", "Xbox Series X");

    when(videojuegoRepository.findById(1L)).thenReturn(Optional.of(videojuego));

    Videojuego resultado = videojuegoService.obtenerPorId(1L);

    assertEquals("Halo", resultado.getTitulo());
    assertEquals("Shooter", resultado.getGenero());
}

    @Test
    public void testObtenerPorId_noExistente() {
        when(videojuegoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(VideojuegoNotFoundException.class, () -> {
            videojuegoService.obtenerPorId(99L);
        });
    }
}