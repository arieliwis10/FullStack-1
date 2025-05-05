package com.ejemplo.micro_mascotas;


// 📥 Importamos la excepción personalizada que será lanzada si una película no se encuentra.
import com.ejemplo.micro_mascotas.exception.EventoNotFoundException;
// 📦 Importamos la clase modelo que representa a una película.
import com.ejemplo.micro_mascotas.model.Evento;
// 🧱 Repositorio que será simulado para estas pruebas.
import com.ejemplo.micro_mascotas.repository.EventoRepository;
// 🎯 Servicio real que vamos a probar.
import com.ejemplo.micro_mascotas.service.EventoService;

// 🧪 Anotaciones necesarias para los métodos de test.
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// 📚 Utilidades de colección para simular listas de películas.
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

// ✅ Métodos estáticos para hacer afirmaciones (verificaciones) en los tests.
import static org.junit.jupiter.api.Assertions.*;
// 🔧 Herramientas para simular comportamiento de objetos con Mockito.
import static org.mockito.Mockito.*;

// 🔢 Clase que permite ordenar resultados al llamar a métodos de repositorio.
import org.springframework.data.domain.Sort;

// 🧪 CLASE PRINCIPAL DE PRUEBAS
// Esta clase contiene pruebas unitarias para validar el comportamiento de la clase EventoService.
// La idea es que aquí podamos probar toda la lógica de negocio de ese servicio, sin necesidad de correr toda la app ni conectarse a una base de datos real.
public class EventoServiceTest {

    // 🔧 Dependencia simulada (mock) del repositorio
    private EventoRepository eventoRepository;

    // 🎯 Servicio real que vamos a probar
    private EventoService eventoService;

    // 🔁 Este método se ejecuta antes de cada prueba. Se usa para configurar el
    // entorno de test.
    // Creamos un mock del repositorio y lo inyectamos en el servicio.
    @BeforeEach
    public void setUp() {
        eventoRepository = mock(EventoRepository.class);
        eventoService = new EventoService(eventoRepository);
    }

    // ✅ PRUEBA: Obtener todos los eventos
    // Esta prueba valida que el método "obtenerTodas()" del servicio funciona
    // correctamente.
    @Test
    public void testObtenerTodas() {
        // 🎥 Simulamos dos eventos como si vinieran de la base de datos
        Evento p1 = new Evento(1L, "Evento 1", "04/04/2025", "Competicion", "Parque Metropolitano", Arrays.asList("Kevin", "Max"));
        Evento p2 = new Evento(2L, "Evento 2", "04/04/2025", "Carrera", "Club Hipico", Arrays.asList("Luna", "Lala"));

        // 🧪 Indicamos que cuando se llame al método findAll() ordenado por ID, se
        // devolverá esta lista.
        when(eventoRepository.findAll(Sort.by("id").ascending())).thenReturn(Arrays.asList(p1, p2));

        // 🎯 Ejecutamos el método real del servicio
        List<Evento> resultado = eventoService.obtenerTodas();

        // ✅ Validamos los resultados esperados
        assertEquals(2, resultado.size()); // Esperamos 2 películas
        assertEquals("Evento 1", resultado.get(0).getNombre()); // Y que la primera sea "Pelicula 1"
    }

    // ✅ PRUEBA: Obtener una película existente por su ID
    @Test
    public void testObtenerPorId_existente() {
        // 🎥 Simulamos una película existente
        Evento evento = new Evento(1L, "Evento Test", "04/04/2025", "Director X", "Drama", Arrays.asList("Sila", "Nola"));

        // 🧪 Configuramos el mock: si se busca el ID 1, devuelve esa película
        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));

        // 🎯 Llamamos al servicio para obtener la película
        Evento resultado = eventoService.obtenerPorId(1L);

        // ✅ Verificamos que los datos coincidan
        assertEquals("Evento Test", resultado.getNombre());
        assertEquals("04/04/2025", resultado.getFecha());
    }

    // ❌ PRUEBA: Obtener una película por ID cuando NO existe
    // Aquí validamos que el servicio lanza correctamente la excepción personalizada
    // cuando no se encuentra la película.
    @Test
    public void testObtenerPorId_noExistente() {
        // 🧪 Simulamos que el repositorio no encuentra nada para el ID 99
        when(eventoRepository.findById(99L)).thenReturn(Optional.empty());

        // 🚨 Esperamos que se lance una excepción del tipo EventoNotFoundException
        assertThrows(EventoNotFoundException.class, () -> {
            eventoService.obtenerPorId(99L);
        });
    }
}
