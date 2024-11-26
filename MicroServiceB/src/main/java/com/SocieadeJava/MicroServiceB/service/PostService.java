package com.SocieadeJava.MicroServiceB.service;

import com.SocieadeJava.MicroServiceB.entity.Post;
import com.SocieadeJava.MicroServiceB.exceptions.ResourceInUseException;
import com.SocieadeJava.MicroServiceB.exceptions.ResourceNotFoundException;
import com.SocieadeJava.MicroServiceB.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        try {
            if (postRepository.existsById(id)) {
                postRepository.deleteById(id);
            } else {
                throw new ResourceNotFoundException("Post não encontrado com ID: " + id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new ResourceInUseException("O post " + id + " está  referenciado em outra parte do sistema.");
        }
    }
}
