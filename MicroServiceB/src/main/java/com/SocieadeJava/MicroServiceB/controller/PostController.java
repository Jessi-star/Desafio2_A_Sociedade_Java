package com.SocieadeJava.MicroServiceB.controller;

import com.SocieadeJava.MicroServiceB.dto.PostDTO;
import com.SocieadeJava.MicroServiceB.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    // Injeção de dependência do serviço de Post
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * Retorna todos os posts (banco de dados + API externa).
     * @return Lista de PostDTO com todos os posts
     */
    @GetMapping("/posts")
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    /**
     * Retorna um post específico pelo ID.
     * @param id ID do post
     * @return PostDTO com as informações do post
     */
    @GetMapping("/posts/{id}")
    public PostDTO getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    /**
     * Cria um novo post.
     * @param postDTO Dados do post a ser criado
     * @return PostDTO com as informações do post criado
     */
    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDTO createPost(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }

    /**
     * Atualiza um post existente.
     * @param id ID do post a ser atualizado
     * @param postDTO Dados atualizados do post
     * @return PostDTO com o post atualizado
     */
    @PutMapping("/posts/{id}")
    public PostDTO updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        return postService.updatePost(id, postDTO);
    }

    /**
     * Deleta um post pelo ID.
     * @param id ID do post a ser deletado
     */
    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @PostMapping("/sync-data")
    public List<PostDTO> syncExternalPosts() {
        return postService.syncExternalPosts();
    }
}
