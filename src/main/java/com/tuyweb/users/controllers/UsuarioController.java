package com.tuyweb.users.controllers;

import com.tuyweb.users.entities.Usuario;
import com.tuyweb.users.interfaces.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioRepository repository;

    @GetMapping("/obtener")
    public List<Usuario> saluda(){
        return repository.findAll();
    }

    @PostMapping
    public Usuario createUser(@RequestBody Usuario usuario){
        return repository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        repository.findById(id).map(user->{
            repository.delete(user);
            return null;
        });
        return "Eliminado correctamente";
    }

    @PutMapping("/{id}")
    public Usuario updateUser(@RequestBody Usuario usuario, @PathVariable Long id){

        return repository.findById(id).map(user->{
            user.setEmail(usuario.getEmail());
            user.setPassword(usuario.getPassword());
            return repository.save(user);
        }).orElse(null);

    }

}
