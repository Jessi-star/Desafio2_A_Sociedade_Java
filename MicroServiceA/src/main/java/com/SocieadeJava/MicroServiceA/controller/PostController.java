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
            @ApiResponse(responseCode = "500", description = "server error")
    })
    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        try {
            List<PostDTO> posts = postService.fetchAllPosts();
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); //  server error
        }
    }

    @Operation(summary = "Create a new post", description = "Creates a new post in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the post"),
            @ApiResponse(responseCode = "400", description = "Invalid input data provided"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        try {
            PostDTO createdPost = postService.createPost(postDTO);
            return ResponseEntity.status(201).body(createdPost); // 201 Created
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); //  server error
        }
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
        try {
            PostDTO updatedPost = postService.updatePost(id, postDTO);
            if (updatedPost == null) {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
            return ResponseEntity.ok(updatedPost); // 200 OK
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // 500 Internal server error
        }
    }

    @Operation(summary = "Delete a post", description = "Delete a post from the system by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the post"),
            @ApiResponse(responseCode = "404", description = "Post not found for the provided ID"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        try {

            postService.deletePost(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // 500
        }
    }

    @Operation(summary = "Retrieve a post by ID", description = "Fetch a specific post from the system by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the post"),
            @ApiResponse(responseCode = "404", description = "Post not found for the provided ID"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        try {
            PostDTO post = postService.getPostById(id);
            if (post == null) {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
            return ResponseEntity.ok(post); // 200 OK
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); //erver error
        }
    }
}


