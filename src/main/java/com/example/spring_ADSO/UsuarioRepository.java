package com.example.spring_ADSO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de usuarios.
 * Al extender de JpaRepository, Spring Data ya incluye sin escribir código:
 * save (guardar y actualizar), findAll (listar), findById (buscar por id),
 * existsById (verificar si existe) y deleteById (eliminar).
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por su correo electrónico.
     * Spring Data genera la consulta SQL a partir del nombre del método.
     *
     * @param correo correo que se desea buscar
     * @return el usuario si existe, o un Optional vacío si no
     */
    Optional<Usuario> findByCorreo(String correo);
}
