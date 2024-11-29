package com.SocieadeJava.MicroServiceA.controller;

import com.SocieadeJava.MicroServiceA.dto.PostDTO;
import com.SocieadeJava.MicroServiceA.service.PostService;
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

    @PutMapping("/posts/{id}")
    public PostDTO updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        return postService.updatePost(id, postDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

}
