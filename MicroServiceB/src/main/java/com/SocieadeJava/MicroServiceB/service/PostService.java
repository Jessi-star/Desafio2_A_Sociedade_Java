package com.SocieadeJava.MicroServiceB.service;

import com.SocieadeJava.MicroServiceB.dto.PostDTO;
import com.SocieadeJava.MicroServiceB.entity.Post;
import com.SocieadeJava.MicroServiceB.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

        return new PostDTO(post);
    }

    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostDTO::new)
                .collect(Collectors.toList());
    }

    public PostDTO getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return new PostDTO(post.orElse(null));
    }

    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.setTitulo(postDTO.getTitle());
            post.setConteudo(postDTO.getBody());
            postRepository.save(post);
            return new PostDTO(post);
        }

        return null;
    }

    public void deletePost(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            postRepository.delete(post);
        }
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElseThrow(() -> new RuntimeException("Postagem não encontrada com ID: " + id));
    }
}
