package com.example.spring_ADSO;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * Carga usuarios de prueba cuando la aplicación arranca.
 * CommandLineRunner ejecuta el método run() automáticamente al iniciar Spring Boot.
 */
@Component
@RequiredArgsConstructor
public class CargadorDatos implements CommandLineRunner {

    // Repositorio para guardar los usuarios iniciales (Spring lo inyecta solo)
    private final UsuarioRepository usuarioRepository;

    /**
     * Inserta tres usuarios iniciales solo si la tabla está vacía,
     * para no duplicar datos cada vez que se reinicia la aplicación.
     */
    @Override
    public void run(String... args) {
        if (usuarioRepository.count() == 0) {
            usuarioRepository.save(new Usuario("Administrador", "admin@tienda.com", "1234"));
            usuarioRepository.save(new Usuario("Vendedor Uno", "vendedor1@tienda.com", "5678"));
            usuarioRepository.save(new Usuario("Vendedor Dos", "vendedor2@tienda.com", "9876"));
            System.out.println("Datos iniciales cargados: 3 usuarios");
        } else {
            System.out.println("La tabla ya tiene datos: no se cargan datos iniciales");
        }
    }
}
