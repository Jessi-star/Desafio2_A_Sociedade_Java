package com.SocieadeJava.MicroServiceB.service;

import com.SocieadeJava.MicroServiceB.client.JsonPlaceholderClient;
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
    private  final JsonPlaceholderClient jsonPlaceholderClient;

    @Autowired
    public PostService(PostRepository postRepository, JsonPlaceholderClient jsonPlaceholderClient) {
        this.postRepository = postRepository;
        this.jsonPlaceholderClient = jsonPlaceholderClient;
    }

    public PostDTO createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setTitulo(postDTO.getTitle());
        post.setConteudo(postDTO.getBody());
        post = postRepository.save(post);

        return PostDTO.fromEntity(post);
    }

    public List<PostDTO> getAllPosts() {
        List<PostDTO> localPosts = postRepository.findAll().stream()
                .map(PostDTO::fromEntity)
                .collect(Collectors.toList());

        List<PostDTO> externalPosts = jsonPlaceholderClient.getAllPosts();

        localPosts.addAll(externalPosts);
        return localPosts;
    }

    public PostDTO getPostById(Long id) {

        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            return PostDTO.fromEntity(post);
        }

        PostDTO externalPost = jsonPlaceholderClient.getPostById(id);
        if (externalPost == null) {
            throw new ResourceNotFoundException("Post não encontrado com ID: " + id);
        }

        return externalPost;
    }

    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post não encontrado com ID: " + id));

        if (postDTO.getTitle() == null || postDTO.getBody() == null) {
            throw new IllegalArgumentException("Título e conteúdo não podem ser nulos.");
        }

        post.setTitulo(postDTO.getTitle());
        post.setConteudo(postDTO.getBody());
        post = postRepository.save(post);

        return PostDTO.fromEntity(post);
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post não encontrado com ID: " + id));

        try {
            postRepository.delete(post);
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceInUseException("Não é possível excluir o post, pois ele está sendo utilizado por outro recurso.");
        }
    }

    public List<PostDTO> syncExternalPosts() {
        List<PostDTO> externalPosts = jsonPlaceholderClient.getAllPosts();

        externalPosts.forEach(postDTO -> {
            Post post = new Post();
            post.setTitulo(postDTO.getTitle());
            post.setConteudo(postDTO.getBody());
            postRepository.save(post);
        });

        return externalPosts;
    }
}

