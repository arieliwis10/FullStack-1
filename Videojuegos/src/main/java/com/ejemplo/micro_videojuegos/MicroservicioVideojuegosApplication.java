// -------------------- PAQUETE -------------------------

// Indica que esta clase pertenece al paquete raíz del microservicio
package com.ejemplo.micro_videojuegos;

// -------------------- IMPORTACIONES -------------------------

// Importamos la clase SpringApplication que es la encargada de iniciar la aplicación Spring Boot
import org.springframework.boot.SpringApplication;

// Importamos la anotación que configura automáticamente la aplicación como un proyecto Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication;

// -------------------- CLASE PRINCIPAL -------------------------

/**
 * Clase principal que da inicio al microservicio.
 * Aquí es donde arranca toda la aplicación.
 */
@SpringBootApplication // Indica a Spring Boot que debe hacer:
						// - Escaneo automático de componentes
						// - Configuración automática (autoconfiguration)
						// - Registro de beans en el contexto de Spring
public class MicroservicioVideojuegosApplication {

	/**
	 * Método main.
	 * Es el punto de entrada de cualquier aplicación Java.
	 * En este caso, además de ser el método principal, inicia el servidor web
	 * embebido (Tomcat) y
	 * configura automáticamente todo el microservicio.
	 */
	public static void main(String[] args) {
		// Aquí es donde Spring Boot arranca la aplicación
		// Configura las rutas, servicios, controladores y deja lista la API para
		// recibir peticiones HTTP
		SpringApplication.run(MicroservicioVideojuegosApplication.class, args);
	}

}

