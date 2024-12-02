package com.SocieadeJava.MicroServiceA.controller;

import com.SocieadeJava.MicroServiceA.dto.PostDTO;
import com.SocieadeJava.MicroServiceA.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Retrieve all posts", description = "Fetch a list of all posts from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of posts"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.fetchAllPosts();
    }

    @Operation(summary = "Create a new post", description = "Creates a new post in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the post"),
            @ApiResponse(responseCode = "400", description = "Invalid input data provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public PostDTO createPost(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }

    @Operation(summary = "Update an existing post", description = "Update an existing post in the system by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the post"),
            @ApiResponse(responseCode = "404", description = "Post not found for the provided ID"),
            @ApiResponse(responseCode = "400", description = "Invalid data provided for update"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(
            @Parameter(description = "ID of the post to be updated", required = true) @PathVariable Long id,
            @RequestBody PostDTO postDTO) {

        PostDTO updatedPost = postService.updatePost(id, postDTO);

        if (updatedPost == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPost);
    }

    @Operation(summary = "Delete a post", description = "Delete a post from the system by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the post"),
            @ApiResponse(responseCode = "404", description = "Post not found for the provided ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @Operation(summary = "Retrieve a post by ID", description = "Fetch a specific post from the system by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the post"),
            @ApiResponse(responseCode = "404", description = "Post not found for the provided ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }
}


