package com.SocieadeJava.MicroServiceA.intraclient;

import com.SocieadeJava.MicroServiceA.dto.PostDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microserviceb", url = "${microserviceb.url}")
public interface PostClient {

    @GetMapping("/posts")
    List<PostDTO> getAllPosts();

    @PostMapping("/posts")
    PostDTO createPost(@RequestBody PostDTO postDTO);

    @PutMapping("/posts/{id}")
    PostDTO updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO);

    @DeleteMapping("/posts/{id}")
    void deletePost(@PathVariable Long id);

    @GetMapping("/posts/{id}")
    PostDTO getPostById(@PathVariable Long id);
}
