package com.SocieadeJava.MicroServiceB.service;

import com.SocieadeJava.MicroServiceB.client.JsonPlaceholderClient;
import com.SocieadeJava.MicroServiceB.dto.PostDTO;
import com.SocieadeJava.MicroServiceB.entity.Post;
import com.SocieadeJava.MicroServiceB.exceptions.ResourceNotFoundException;
import com.SocieadeJava.MicroServiceB.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private JsonPlaceholderClient jsonPlaceholderClient;

    @InjectMocks
    private PostService postService;

    private Post post;
    private PostDTO postDTO;

    @BeforeEach
    void setUp() {
        post = new Post(1L, "Título de Teste", "Corpo do Teste");

    }

    @Test
    void testarBuscarTodosPosts_deveRetornarListaVazia() {
        when(postRepository.findAll()).thenReturn(Collections.emptyList());

        List<PostDTO> posts = postService.getAllPosts();

        assertTrue(posts.isEmpty(), "A lista deve estar vazia");
        verify(postRepository, times(1)).findAll();
    }


    @Test
    void testarBuscarPostPorId_deveLancarExcecaoQuandoNaoEncontrado() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> postService.getPostById(1L));

        verify(postRepository, times(1)).findById(1L);
    }


    @Test
    void testarAtualizarPost_deveLancarExcecaoQuandoNaoEncontrado() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> postService.updatePost(1L, postDTO));

        verify(postRepository, times(1)).findById(1L);
        verify(postRepository, times(0)).save(any(Post.class));
    }


    @Test
    void testarDeletarPost_deveLancarExcecaoQuandoNaoEncontrado() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> postService.deletePost(1L));

        verify(postRepository, times(1)).findById(1L);
        verify(postRepository, times(0)).deleteById(1L);
    }
}
