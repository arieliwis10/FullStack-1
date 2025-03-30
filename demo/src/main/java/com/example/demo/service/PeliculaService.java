package com.example.demo.service;

import com.example.demo.modelo.Pelicula;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;;

@Service
public class PeliculaService {
    
    private final List<Pelicula> peliculas = new ArrayList<>();

    public PeliculaService(){
        peliculas.add(new Pelicula(1L, "Interestellar", 2014, "Christopher Nolan", "Ciencia Ficcion", "Exploracion del espacio y tiempo."));
        peliculas.add(new Pelicula(2L, "Inception", 2010, "Christopher Nolan", "Accion", "Sueños dentro de sueños."));

    }

    public List<Pelicula> obtenerTodas(){
        return peliculas;
    }

    public Optional<Pelicula> obtenerPorId(Long id){
        return peliculas.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
