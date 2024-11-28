package com.SocieadeJava.MicroServiceA.service;


import com.SocieadeJava.MicroServiceA.dto.PostDTO;
import com.SocieadeJava.MicroServiceA.intraclient.PostClient;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostClient postClient;

    public PostService(PostClient postClient) {
        this.postClient = postClient;
    }

    public List<PostDTO> fetchAllPosts() {
        return postClient.getAllPosts();
    }

    public PostDTO createPost(PostDTO postDTO) {
        return postClient.createPost(postDTO);
    }

    public PostDTO updatePost(Long id, PostDTO postDTO) {
        return postClient.updatePost(id, postDTO);
    }
}