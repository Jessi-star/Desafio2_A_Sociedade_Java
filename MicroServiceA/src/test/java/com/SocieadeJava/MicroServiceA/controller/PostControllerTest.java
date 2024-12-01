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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    private PostDTO postDTO;

    @BeforeEach
    void config(){
        postDTO = new PostDTO(1L, "Teste", "Testando teste");
    }

    @Test
    void testarCriarPost_deveRetornarPostCriado() {
        when(postService.createPost(postDTO)).thenReturn(postDTO);

        PostDTO resposta = postController.createPost(postDTO);

        assertNotNull(resposta, "A resposta não deve ser nula");
        assertEquals(postDTO.getTitle(), resposta.getTitle(), "Os títulos devem ser iguais");
        assertEquals(postDTO.getBody(), resposta.getBody(), "Os corpos devem ser iguais");

        verify(postService, times(1)).createPost(postDTO);
    }

    
}
