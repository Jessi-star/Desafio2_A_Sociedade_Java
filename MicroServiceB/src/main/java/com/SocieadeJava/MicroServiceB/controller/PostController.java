package com.SocieadeJava.MicroServiceB.controller;

import com.SocieadeJava.MicroServiceB.dto.PostDTO;
import com.SocieadeJava.MicroServiceB.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "Retrieve all posts", description = "Retur list of posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of posts")
    })

    @GetMapping("/posts")
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @Operation(summary = "Ger a post ID", description = "Return a post by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully return the post"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })

    @GetMapping("/posts/{id}")
    public PostDTO getPostById(
        @Parameter(description = "ID of the post to be retrieved", required = true)
        @PathVariable Long id){

        return postService.getPostById(id);
    }


    @Operation(summary = "Create new post", description = "Creates a new post in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the post ")
    })
    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDTO createPost(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }
    @Operation(summary = "Update a post", description = "Update a post in system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the post"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })

    @PutMapping("/posts/{id}")
    public PostDTO updatePost(
            @Parameter(description = "ID of the post to be updated", required = true)
            @PathVariable Long id,
            @RequestBody PostDTO postDTO) {
        return postService.updatePost(id, postDTO);
    }

    @Operation(summary = "Delete a post",description = "Delete a post in system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the post"),
            @ApiResponse(responseCode = "404", description = " Post not found")
    })

    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @Operation(summary = "Synchronize external post", description = "Synchronize posts from external source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post synchronized")
    })

    @PostMapping("/sync-data")
    public List<PostDTO> syncExternalPosts() {
        return postService.syncExternalPosts();
    }
}
