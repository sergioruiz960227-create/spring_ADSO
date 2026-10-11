package com.example.spring_ADSO;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * Controlador REST del módulo de usuarios.
 * Ruta base: /usuarios
 * Operaciones: obtener, guardar, actualizar y eliminar.
 */
@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    // Repositorio que se comunica con la tabla de usuarios (Spring lo inyecta solo)
    private final UsuarioRepository usuarioRepository;

    /**
     * Obtener: devuelve todos los usuarios guardados.
     * Ruta: /usuarios/todos
     */
    @GetMapping("/todos")
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Obtener: busca un usuario por su id.
     * Ruta: /usuarios/buscar/{id}
     */
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable("id") Long id) {
        Optional<Usuario> encontrado = usuarioRepository.findById(id);
        if (encontrado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un usuario con id " + id);
        }
        return ResponseEntity.ok(encontrado.get());
    }

    /**
     * Guardar: crea un usuario nuevo con los datos enviados en la dirección.
     * Ruta: /usuarios/guardar?nombre=...&correo=...&clave=...
     */
    @GetMapping("/guardar")
    public ResponseEntity<?> guardarUsuario(@RequestParam("nombre") String nombre,
                                            @RequestParam("correo") String correo,
                                            @RequestParam("clave") String clave) {
        // El correo no se puede repetir
        if (usuarioRepository.findByCorreo(correo).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe un usuario con el correo " + correo);
        }
        Usuario nuevoUsuario = new Usuario(nombre, correo, clave);
        return ResponseEntity.ok(usuarioRepository.save(nuevoUsuario));
    }

    /**
     * Actualizar: modifica los datos de un usuario existente.
     * Ruta: /usuarios/actualizar/{id}?nombre=...&correo=...&clave=...
     */
    @GetMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable("id") Long id,
                                               @RequestParam("nombre") String nombre,
                                               @RequestParam("correo") String correo,
                                               @RequestParam("clave") String clave) {
        Optional<Usuario> encontrado = usuarioRepository.findById(id);
        if (encontrado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un usuario con id " + id);
        }

        // El correo nuevo no puede pertenecer a otro usuario
        Optional<Usuario> conMismoCorreo = usuarioRepository.findByCorreo(correo);
        if (conMismoCorreo.isPresent() && !conMismoCorreo.get().getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ese correo ya lo usa otro usuario");
        }

        // Se cambian los datos y se guarda (save actualiza porque el id ya existe)
        Usuario existente = encontrado.get();
        existente.setNombre(nombre);
        existente.setCorreo(correo);
        existente.setClave(clave);
        return ResponseEntity.ok(usuarioRepository.save(existente));
    }

    /**
     * Eliminar: borra un usuario por su id.
     * Ruta: /usuarios/eliminar/{id}
     */
    @GetMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable("id") Long id) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un usuario con id " + id);
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok("Usuario " + id + " eliminado correctamente");
    }
}
