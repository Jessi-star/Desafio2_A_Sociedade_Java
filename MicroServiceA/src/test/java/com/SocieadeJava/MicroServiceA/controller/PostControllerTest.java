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

    // Teste para criar um post com sucesso
    @Test
    void testarCriarPost_deveRetornarPostCriado() {
        // Simula o comportamento do serviço para retornar o postDTO quando o método createPost for chamado
        when(postService.createPost(postDTO)).thenReturn(postDTO);

        PostDTO resposta = postController.createPost(postDTO);

        assertNotNull(resposta, "A resposta não deve ser nula");
        assertEquals(postDTO.getTitle(), resposta.getTitle(), "Os títulos devem ser iguais");
        assertEquals(postDTO.getBody(), resposta.getBody(), "Os corpos devem ser iguais");

        verify(postService, times(1)).createPost(postDTO);
    }

    // Teste para obter um post por ID com sucesso
    @Test
    void testarObterPostPorId_deveRetornarPostExistente() {
        // Simula o comportamento do serviço para retornar o postDTO quando o método getPostById for chamado
        when(postService.getPostById(1L)).thenReturn(postDTO);

        PostDTO resposta = postController.getPostById(1L);

        assertNotNull(resposta, "A resposta não deve ser nula");
        assertEquals(postDTO.getTitle(), resposta.getTitle(), "Os títulos devem ser iguais");
        assertEquals(postDTO.getBody(), resposta.getBody(), "Os corpos devem ser iguais");

        verify(postService, times(1)).getPostById(1L);
    }

    // Teste para atualizar um post com sucesso
    @Test
    void testarAtualizarPost_deveRetornarPostAtualizado() {
        // Simula o comportamento do serviço para retornar o postDTO quando o método updatePost for chamado
        when(postService.updatePost(1L, postDTO)).thenReturn(postDTO);

        ResponseEntity<PostDTO> resposta = postController.updatePost(1L, postDTO);

        assertNotNull(resposta.getBody(), "O corpo da resposta não deve ser nulo");
        assertEquals(200, resposta.getStatusCodeValue(), "O código de status deve ser 200 OK");
        assertEquals(postDTO.getTitle(), resposta.getBody().getTitle(), "Os títulos devem ser iguais");
        assertEquals(postDTO.getBody(), resposta.getBody().getBody(), "Os corpos devem ser iguais");

        verify(postService, times(1)).updatePost(1L, postDTO);
    }

    // Teste para deletar um post com sucesso
    @Test
    void testarDeletarPost_deveRetornarTrueQuandoSucesso() {
        
        // Simula o comportamento do serviço para não retornar nada quando o método deletePost for chamado
        doNothing().when(postService).deletePost(1L);

        postController.deletePost(1L);

        verify(postService, times(1)).deletePost(1L);
    }


}
