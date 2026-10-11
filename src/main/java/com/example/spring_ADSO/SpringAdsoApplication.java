package com.example.spring_ADSO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal del módulo de usuarios.
 * La anotación @SpringBootApplication activa la configuración automática de Spring Boot
 * y busca los componentes (controladores, repositorios, etc.) en este paquete
 * y en sus subpaquetes.
 */
@SpringBootApplication
public class SpringAdsoApplication {

    /**
     * Punto de entrada: arranca el servidor web integrado (Tomcat) y la aplicación.
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringAdsoApplication.class, args);
    }

}
