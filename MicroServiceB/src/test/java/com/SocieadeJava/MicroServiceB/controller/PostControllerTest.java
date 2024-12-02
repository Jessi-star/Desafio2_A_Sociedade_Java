package com.SocieadeJava.MicroServiceB.controller;

import com.SocieadeJava.MicroServiceB.dto.PostDTO;
import com.SocieadeJava.MicroServiceB.exceptions.ResourceNotFoundException;
import com.SocieadeJava.MicroServiceB.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PostControllerTest {

    private PostService postService;
    private PostController postController;

    @BeforeEach
    void setUp() {
        postService = mock(PostService.class);
        postController = new PostController(postService);
    }

    @Test
    void testGetAllPosts() {
        // Arrange
        List<PostDTO> mockPosts = Arrays.asList(
                new PostDTO("Title 1", "Content 1", 1L),
                new PostDTO("Title 2", "Content 2", 2L)
        );
        when(postService.getAllPosts()).thenReturn(mockPosts);

        // Act
        ResponseEntity<List<PostDTO>> response = postController.getAllPosts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPosts.size(), response.getBody().size());
        verify(postService, times(1)).getAllPosts();
    }

    @Test
    void testGetPostById_Success() {
        // Arrange
        Long postId = 1L;
        PostDTO mockPost = new PostDTO("Title", "Content", postId);
        when(postService.getPostById(postId)).thenReturn(mockPost);

        // Act
        ResponseEntity<PostDTO> response = postController.getPostById(postId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPost, response.getBody());
        verify(postService, times(1)).getPostById(postId);
    }

    @Test
    void testGetPostById_NotFound() {
        // Arrange
        Long postId = 1L;
        when(postService.getPostById(postId)).thenThrow(new ResourceNotFoundException("Post not found"));

        // Act
        ResponseEntity<PostDTO> response = postController.getPostById(postId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(postService, times(1)).getPostById(postId);
    }

    @Test
    void testCreatePost() {
        // Arrange
        PostDTO newPost = new PostDTO("New Title", "New Content", null);
        PostDTO createdPost = new PostDTO("New Title", "New Content", 1L);
        when(postService.createPost(newPost)).thenReturn(createdPost);

        // Act
        ResponseEntity<PostDTO> response = postController.createPost(newPost);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdPost, response.getBody());
        verify(postService, times(1)).createPost(newPost);
    }

    @Test
    void testUpdatePost() {
        // Arrange
        Long postId = 1L;
        PostDTO updatedPost = new PostDTO("Updated Title", "Updated Content", postId);
        when(postService.updatePost(postId, updatedPost)).thenReturn(updatedPost);

        // Act
        ResponseEntity<PostDTO> response = postController.updatePost(postId, updatedPost);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedPost, response.getBody());
        verify(postService, times(1)).updatePost(postId, updatedPost);
    }

    @Test
    void testDeletePost() {
        // Arrange
        Long postId = 1L;
        doNothing().when(postService).deletePost(postId);

        // Act
        ResponseEntity<Void> response = postController.deletePost(postId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(postService, times(1)).deletePost(postId);
    }
}
