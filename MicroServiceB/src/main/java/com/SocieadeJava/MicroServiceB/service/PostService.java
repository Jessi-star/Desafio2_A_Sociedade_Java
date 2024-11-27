package com.SocieadeJava.MicroServiceB.service;

import com.SocieadeJava.MicroServiceB.dto.PostDTO;
import com.SocieadeJava.MicroServiceB.entity.Post;
import com.SocieadeJava.MicroServiceB.exceptions.ResourceInUseException;
import com.SocieadeJava.MicroServiceB.exceptions.ResourceNotFoundException;
import com.SocieadeJava.MicroServiceB.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDTO createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setTitulo(postDTO.getTitle());
        post.setConteudo(postDTO.getBody());
        post = postRepository.save(post);

        // Incluindo o ID do post no DTO ao retornar
        return PostDTO.fromEntity(post);
    }

    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostDTO::fromEntity) // Usando o método estático para mapear
                .collect(Collectors.toList());
    }

    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post não encontrado com ID: " + id));
        return PostDTO.fromEntity(post);
    }

    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post não encontrado com ID: " + id));

        post.setTitulo(postDTO.getTitle());
        post.setConteudo(postDTO.getBody());
        postRepository.save(post);

        return PostDTO.fromEntity(post);
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post não encontrado com ID: " + id));

        try {
            postRepository.delete(post);
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceInUseException("Não é possível excluir o post pois ele está sendo usado por outro recurso.");
        }
    }
}
