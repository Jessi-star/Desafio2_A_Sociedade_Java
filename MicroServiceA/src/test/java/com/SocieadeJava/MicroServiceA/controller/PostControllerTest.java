package com.SocieadeJava.MicroServiceA.controller;

import com.SocieadeJava.MicroServiceA.dto.PostDTO;
import com.SocieadeJava.MicroServiceA.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    private PostDTO postDTO;

    @BeforeEach
    void setup() {
        postDTO = new PostDTO(1L, "Test", "Testing test");
    }

    // Test to create a post successfully
    @Test
    void testCreatePost_shouldReturnCreatedPost() {
        // Simulates the service behavior to return the postDTO when the createPost method is called
        when(postService.createPost(postDTO)).thenReturn(postDTO);

        PostDTO response = postController.createPost(postDTO).getBody();

        assertNotNull(response, "The response should not be null");
        assertEquals(postDTO.getTitle(), response.getTitle(), "The titles should be the same");
        assertEquals(postDTO.getBody(), response.getBody(), "The bodies should be the same");

        verify(postService, times(1)).createPost(postDTO);
    }

    @Test
    void testCreatePost_shouldReturnErrorWhenInvalidData() {
        postDTO.setTitle(""); // Empty title is invalid

        // Simulates the validation throwing an exception
        doThrow(new IllegalArgumentException("Title is required"))
                .when(postService).createPost(postDTO);

        try {
            postController.createPost(postDTO);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Title is required"),
                    "The error message should indicate that the title is required");
        }
        verify(postService, times(1)).createPost(postDTO);
    }

    // Test to get a post by ID successfully
    @Test
    void testGetPostById_shouldReturnExistingPost() {
        // Simulates the service behavior to return the postDTO when the getPostById method is called
        when(postService.getPostById(1L)).thenReturn(postDTO);

        PostDTO response = postController.getPostById(1L).getBody();

        assertNotNull(response, "The response should not be null");
        assertEquals(postDTO.getTitle(), response.getTitle(), "The titles should be the same");
        assertEquals(postDTO.getBody(), response.getBody(), "The bodies should be the same");

        verify(postService, times(1)).getPostById(1L);
    }

    // Test to get a post by ID that doesn't exist
    @Test
    void testGetPostById_shouldReturnErrorWhenPostDoesNotExist() {
        when(postService.getPostById(1L)).thenReturn(null);  // Simulates that the post was not found

        PostDTO response = postController.getPostById(1L).getBody();

        assertNull(response, "The response should be null when the post is not found");
        verify(postService, times(1)).getPostById(1L);
    }

    // Test to get all posts successfully
    @Test
    void testGetAllPosts_shouldReturnListOfPosts() {
        // Simulates a list of PostDTOs
        List<PostDTO> simulatedPosts = List.of(
                new PostDTO(1L, "Title 1", "Content 1"),
                new PostDTO(2L, "Title 2", "Content 2")
        );

        // Configures the simulated service behavior
        when(postService.fetchAllPosts()).thenReturn(simulatedPosts);

        // Calls the controller method
        List<PostDTO> response = postController.getAllPosts().getBody();

        assertNotNull(response, "The list of posts should not be null");
        assertEquals(2, response.size(), "The list should contain 2 posts");
        assertEquals("Title 1", response.get(0).getTitle(), "The title of the first post should be 'Title 1'");
        assertEquals("Title 2", response.get(1).getTitle(), "The title of the second post should be 'Title 2'");

        verify(postService, times(1)).fetchAllPosts();
    }

    // Test to check if the controller returns an empty list when there are no posts
    @Test
    void testGetAllPosts_shouldReturnEmptyListWhenNoPostsExist() {
        // Simulates the service behavior to return an empty list
        when(postService.fetchAllPosts()).thenReturn(new ArrayList<>());

        // Calls the controller method
        List<PostDTO> response = postController.getAllPosts().getBody();

        assertNotNull(response, "The response should not be null");
        assertTrue(response.isEmpty(), "The list of posts should be empty");

        verify(postService, times(1)).fetchAllPosts();
    }

    // Test to update a post successfully
    @Test
    void testUpdatePost_shouldReturnUpdatedPost() {
        // Simulates the service behavior to return the postDTO when the updatePost method is called
        when(postService.updatePost(1L, postDTO)).thenReturn(postDTO);

        ResponseEntity<PostDTO> response = postController.updatePost(1L, postDTO);

        assertNotNull(response.getBody(), "The response body should not be null");
        assertEquals(200, response.getStatusCodeValue(), "The status code should be 200 OK");
        assertEquals(postDTO.getTitle(), response.getBody().getTitle(), "The titles should be the same");
        assertEquals(postDTO.getBody(), response.getBody().getBody(), "The bodies should be the same");

        verify(postService, times(1)).updatePost(1L, postDTO);
    }

    // Test to throw an error when trying to update a non-existent post.
    @Test
    void testUpdatePost_shouldThrowErrorWhenPostDoesNotExist() {
        // Simulates the service behavior to throw an exception when the updatePost method is called with a non-existent ID
        when(postService.updatePost(1L, postDTO)).thenThrow(new RuntimeException("Post not found"));

        try {
            postController.updatePost(1L, postDTO);
        } catch (Exception e) {
            assertEquals("Post not found", e.getMessage(), "The error message should be 'Post not found'");
        }

        verify(postService, times(1)).updatePost(1L, postDTO);
    }

    // Test to delete a post successfully
    @Test
    void testDeletePost_shouldReturnTrueWhenSuccess() {

        // Simulates the service behavior to return nothing when the deletePost method is called
        doNothing().when(postService).deletePost(1L);

        postController.deletePost(1L);

        verify(postService, times(1)).deletePost(1L);
    }

    // Test to delete a post that does not exist
    @Test
    void testDeletePost_shouldReturnErrorWhenPostDoesNotExist() {
        doThrow(new RuntimeException("Post not found")).when(postService).deletePost(1L);

        try {
            postController.deletePost(1L);
        } catch (Exception e) {
            assertEquals("Post not found", e.getMessage());
        }

        verify(postService, times(1)).deletePost(1L);
    }
}
