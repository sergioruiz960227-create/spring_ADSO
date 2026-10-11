package com.example.spring_ADSO;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Prueba básica: comprueba que el contexto de Spring Boot arranca correctamente.
 * Requiere que MySQL esté encendido, porque la aplicación se conecta a la base de datos.
 */
@SpringBootTest
class SpringAdsoApplicationTests {

    @Test
    void contextLoads() {
        // Si el contexto no puede iniciar, la prueba falla antes de llegar aquí
    }

}
