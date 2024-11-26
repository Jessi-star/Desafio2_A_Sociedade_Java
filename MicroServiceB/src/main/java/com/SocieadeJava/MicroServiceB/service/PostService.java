package com.SocieadeJava.MicroServiceB.service;

import com.SocieadeJava.MicroServiceB.client.JsonPlaceholderClient;
import com.SocieadeJava.MicroServiceB.dto.PostDTO;
import com.SocieadeJava.MicroServiceB.entity.Post;
import com.SocieadeJava.MicroServiceB.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    private final JsonPlaceholderClient jsonPlaceholderClient;

    public PostService(JsonPlaceholderClient jsonPlaceholderClient) {
        this.jsonPlaceholderClient = jsonPlaceholderClient;
    }

    public List<PostDTO> fetchAllPosts() {
        return jsonPlaceholderClient.getAllPosts();
    }

    public PostDTO fetchPostById(Long id) {
        return jsonPlaceholderClient.getPostById(id);
    }
}
