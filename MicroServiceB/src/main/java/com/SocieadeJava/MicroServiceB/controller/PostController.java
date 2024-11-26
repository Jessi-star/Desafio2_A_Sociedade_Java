package com.SocieadeJava.MicroServiceB.controller;


import com.SocieadeJava.MicroServiceB.dto.PostDTO;
import com.SocieadeJava.MicroServiceB.entity.Post;
import com.SocieadeJava.MicroServiceB.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        PostDTO createdPost = postService.createPost(postDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }
}
