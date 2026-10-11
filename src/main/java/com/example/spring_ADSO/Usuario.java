package com.example.spring_ADSO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa a un usuario del sistema.
 * Cada objeto de esta clase corresponde a una fila de la tabla "usuarios".
 */
@Entity                   // Indica a JPA/Hibernate que esta clase se guarda en la base de datos
@Table(name = "usuarios") // Nombre de la tabla que Hibernate crea si no existe
@Data                     // Lombok: genera getters, setters, toString, equals y hashCode
@NoArgsConstructor        // Lombok: constructor vacío (JPA lo necesita para crear objetos al leer de la base de datos)
@AllArgsConstructor       // Lombok: constructor con todos los atributos (id, nombre, correo, clave)
public class Usuario {

    /** Identificador único; la base de datos lo genera automáticamente (autoincremental). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre de la persona. */
    @Column
    private String nombre;

    /** Correo electrónico; no se permiten dos usuarios con el mismo correo. */
    @Column(unique = true)
    private String correo;

    /** Contraseña del usuario (por ahora en texto plano, solo con fines de aprendizaje). */
    @Column
    private String clave;

    /**
     * Constructor para crear un usuario nuevo sin id
     * (la base de datos se lo asigna al guardarlo).
     */
    public Usuario(String nombre, String correo, String clave) {
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
    }
}
