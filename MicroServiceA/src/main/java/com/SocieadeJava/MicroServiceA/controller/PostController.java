package com.SocieadeJava.MicroServiceA.controller;

import com.SocieadeJava.MicroServiceA.dto.PostDTO;
import com.SocieadeJava.MicroServiceA.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.fetchAllPosts();
    }

    @PostMapping
    public PostDTO createPost(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        PostDTO updatedPost = postService.updatePost(id, postDTO);

        if (updatedPost == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }
}

