package com.SocieadeJava.MicroServiceA.controller;

import com.SocieadeJava.MicroServiceA.dto.PostDTO;
import com.SocieadeJava.MicroServiceA.service.PostService;
import com.SocieadeJava.MicroServiceA.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@Tag(name = "Post Controller", description = "Endpoints to manage posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "Retrieve all posts", description = "Returns a list of all posts in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts retrieved successfully"),
    })
    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.fetchAllPosts();
    }

    @Operation(summary = "Create a new post", description = "Adds a new post to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post created successfully", content = @Content(schema = @Schema(implementation = PostDTO.class))),
    })
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        PostDTO createdPost = postService.createPost(postDTO);
        return ResponseEntity.status(201).body(createdPost);
    }

    @Operation(summary = "Update an existing post", description = "Updates the details of a post by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post updated successfully", content = @Content(schema = @Schema(implementation = PostDTO.class))),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        PostDTO updatedPost = postService.updatePost(id, postDTO);

        if (updatedPost == null) {
            throw new ResourceNotFoundException("Post not found with ID: " + id);
        }
        return ResponseEntity.ok(updatedPost);
    }

    @Operation(summary = "Delete a post", description = "Deletes a post by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Post deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Retrieve a post by ID", description = "Fetches the details of a post by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post retrieved successfully", content = @Content(schema = @Schema(implementation = PostDTO.class))),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable Long id) {
        PostDTO postDTO = postService.getPostById(id);

        if (postDTO == null) {
            throw new ResourceNotFoundException("Post not found with ID: " + id);
        }
        return postDTO;
    }
}


