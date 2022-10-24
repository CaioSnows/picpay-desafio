package com.carteiradesafio.carteiraapi.controllers;

import com.carteiradesafio.carteiraapi.models.entities.Usuario;
import com.carteiradesafio.carteiraapi.models.repository.UsuarioRepository;
import com.carteiradesafio.carteiraapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api") // Criando um URL
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/usuario")
    public List<Usuario> listaUsuarios(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/usuario/{id}")
    public Optional<Usuario> listaUsuarioUnico(@PathVariable(value = "id") long id){
        return usuarioRepository.findById(id);
    }

    @PostMapping("/usuario")
    public Usuario salvaUsuario(@RequestBody Usuario usuario){ // Receber o usuário como JSON no corpo da requisição
        try{
            return usuarioService.create(usuario);
        } catch (Exception exception){
            System.out.println(exception.getMessage());

            return null;
        }
    }

    @DeleteMapping(path = "/{id}")
    public void deletaUsuario(@PathVariable long id){ // Outra maneira de fazer o que o listaUsuarioUnico @Path faz
        usuarioRepository.deleteById(id);
    }

    @PutMapping("/usuario")
    public Usuario atualizarUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @GetMapping("/validar")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String cpf, String senha){
        try{
            return usuarioService.validarSenha(cpf, senha);
        } catch (Exception exception){
            System.out.println(exception.getMessage());

            return null;
        }
    }
}
