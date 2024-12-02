package com.SocieadeJava.MicroServiceA.service;

import com.SocieadeJava.MicroServiceA.dto.PostDTO;
import com.SocieadeJava.MicroServiceA.intraclient.PostClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostsServiceTest {
    @Mock
    private PostClient postClient;

    @InjectMocks
    private PostService postService;

    private PostDTO postDTO;

    @BeforeEach
    void setUp(){
        postDTO = new PostDTO(1L, "Test title", "Test content");
    }

    @Test
    void testarCriarPost(){
        when(postClient.createPost(postDTO)).thenReturn(postDTO);

        PostDTO createdPost = postService.createPost(postDTO);

        assertNotNull(createdPost);
        assertEquals(postDTO.getTitle(), createdPost.getTitle());
        assertEquals(postDTO.getBody(), createdPost.getBody());

        verify(postClient, times(1)).createPost(postDTO);
    }

    @Test
    void testarAtualizarPost_deveChamarPostClient(){
        when(postClient.updatePost(1L, postDTO)).thenReturn(postDTO);

        PostDTO createdPost = postService.updatePost(1L, postDTO);

        assertNotNull(createdPost, "O resultado não deve ser nulo");
        assertEquals(postDTO, createdPost, "O post atualizado deve ser retornado corretamente");

        verify(postClient, times(1)).updatePost(1L, postDTO);
    }

    @Test
    void testarExcluirPost_deveChamarPostClient(){
        doNothing().when(postClient).deletePost(1L);

        postService.deletePost(1L);

        verify(postClient, times(1)).deletePost(1L);
    }
}
