package com.fernandosilva.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fernandosilva.workshopmongo.domain.Post;
import com.fernandosilva.workshopmongo.domain.User;
import com.fernandosilva.workshopmongo.dto.UserDTO;
import com.fernandosilva.workshopmongo.servicies.UserService;


@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    /**
     * Retorna todos os usuários.
     *
     * @return Lista de todos os usuários cadastrados
     */
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream().map(UserDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    /**
     * Busca usuário por ID.
     *
     * @param id ID do usuário
     * @return Usuário com o ID fornecido
     */
    @GetMapping(value="/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User user = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

    /**
     * Deleta um usuário.
     *
     * @param id ID do usuário
     * @return Resposta sem conteúdo
     */
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Insere um novo usuário.
     *
     * @param userDto Dados do novo usuário
     * @return URI do recurso criado
     */
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO userDto) {
        User user = service.fromDTO(userDto);
        user = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Atualiza um usuário existente.
     *
     * @param userDto Dados atualizados do usuário
     * @param id ID do usuário
     * @return Resposta sem conteúdo
     */
    @PutMapping(value="/{id}")
    public ResponseEntity<Void> update(
            @RequestBody UserDTO userDto,
            @PathVariable String id) {
        User user = service.fromDTO(userDto);
        user.setId(id);
        user = service.update(user);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retorna os posts de um usuário.
     *
     * @param id ID do usuário
     * @return Lista de posts associados ao usuário
     */
    @GetMapping(value="/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        User user = service.findById(id);
        return ResponseEntity.ok().body(user.getPosts());
    }
}
