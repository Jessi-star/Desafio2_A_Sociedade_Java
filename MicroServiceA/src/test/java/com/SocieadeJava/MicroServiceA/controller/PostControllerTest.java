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
    void config(){
        postDTO = new PostDTO(1L, "Teste", "Testando teste");
    }

    // Teste para criar um post com sucesso
    @Test
    void testarCriarPost_deveRetornarPostCriado() {
        // Simula o comportamento do serviço para retornar o postDTO quando o metodo createPost for chamado
        when(postService.createPost(postDTO)).thenReturn(postDTO);

        PostDTO resposta = postController.createPost(postDTO);

        assertNotNull(resposta, "A resposta não deve ser nula");
        assertEquals(postDTO.getTitle(), resposta.getTitle(), "Os títulos devem ser iguais");
        assertEquals(postDTO.getBody(), resposta.getBody(), "Os corpos devem ser iguais");

        verify(postService, times(1)).createPost(postDTO);
    }

    @Test
    void testarCriarPost_deveRetornarErroQuandoDadosInvalidos() {
        postDTO.setTitle(""); // Título vazio é inválido

        // Simula a validação lançando uma exceção
        doThrow(new IllegalArgumentException("Título é obrigatório"))
                .when(postService).createPost(postDTO);

        try {
            postController.createPost(postDTO);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Título é obrigatório"),
                    "A mensagem de erro deve informar que o título é obrigatório");
        }
        verify(postService, times(1)).createPost(postDTO);
    }


    // Teste para obter um post por ID com sucesso
    @Test
    void testarObterPostPorId_deveRetornarPostExistente() {
        // Simula o comportamento do serviço para retornar o postDTO quando o metodo getPostById for chamado
        when(postService.getPostById(1L)).thenReturn(postDTO);

        PostDTO resposta = postController.getPostById(1L);

        assertNotNull(resposta, "A resposta não deve ser nula");
        assertEquals(postDTO.getTitle(), resposta.getTitle(), "Os títulos devem ser iguais");
        assertEquals(postDTO.getBody(), resposta.getBody(), "Os corpos devem ser iguais");

        verify(postService, times(1)).getPostById(1L);
    }

    // Teste para obter um post por ID que não existe
    @Test
    void testarObterPostPorId_deveRetornarErroQuandoPostNaoExistir() {
        when(postService.getPostById(1L)).thenReturn(null);  // Simula que não encontrou o post

        PostDTO resposta = postController.getPostById(1L);

        assertNull(resposta, "A resposta deve ser nula quando o post não for encontrado");
        verify(postService, times(1)).getPostById(1L);
    }

    @Test
    void testarGetAllPosts_deveRetornarListaDePosts() {
        List<PostDTO> postsSimulados = List.of(
                new PostDTO(1L, "Título 1", "Conteúdo 1"),
                new PostDTO(2L, "Título 2", "Conteúdo 2")
        );

        when(postService.fetchAllPosts()).thenReturn(postsSimulados);

        List<PostDTO> resposta = postController.getAllPosts();

        assertNotNull(resposta, "A lista de posts não deve ser nula");
        assertEquals(2, resposta.size(), "A lista deve conter 2 posts");
        assertEquals("Título 1", resposta.get(0).getTitle(), "O título do primeiro post deve ser 'Título 1'");
        assertEquals("Título 2", resposta.get(1).getTitle(), "O título do segundo post deve ser 'Título 2'");

        verify(postService, times(1)).fetchAllPosts();
    }

    // Teste para atualizar um post com sucesso
    @Test
    void testarAtualizarPost_deveRetornarPostAtualizado() {
        // Simula o comportamento do serviço para retornar o postDTO quando o metodo updatePost for chamado
        when(postService.updatePost(1L, postDTO)).thenReturn(postDTO);

        ResponseEntity<PostDTO> resposta = postController.updatePost(1L, postDTO);

        assertNotNull(resposta.getBody(), "O corpo da resposta não deve ser nulo");
        assertEquals(200, resposta.getStatusCodeValue(), "O código de status deve ser 200 OK");
        assertEquals(postDTO.getTitle(), resposta.getBody().getTitle(), "Os títulos devem ser iguais");
        assertEquals(postDTO.getBody(), resposta.getBody().getBody(), "Os corpos devem ser iguais");

        verify(postService, times(1)).updatePost(1L, postDTO);
    }

    // Teste para lançar um erro ao tentar atualizar um post inexistente.
    @Test
    void testarAtualizarPost_deveLancarErroQuandoPostNaoExistir() {
        // Simula o comportamento do serviço para lançar uma exceção quando o metodo updatePost for chamado com um ID inexistente
        when(postService.updatePost(1L, postDTO)).thenThrow(new RuntimeException("Post não encontrado"));

        try {
            postController.updatePost(1L, postDTO);
        } catch (Exception e) {
            assertEquals("Post não encontrado", e.getMessage(), "A mensagem de erro deve ser 'Post não encontrado'");
        }

        verify(postService, times(1)).updatePost(1L, postDTO);
    }

    // Teste para deletar um post com sucesso
    @Test
    void testarDeletarPost_deveRetornarTrueQuandoSucesso() {

        // Simula o comportamento do serviço para não retornar nada quando o metodo deletePost for chamado
        doNothing().when(postService).deletePost(1L);

        postController.deletePost(1L);

        verify(postService, times(1)).deletePost(1L);
    }

    // Teste para deletar um post que não existe
    @Test
    void testarDeletarPost_deveRetornarErroQuandoPostNaoExistir() {
        doThrow(new RuntimeException("Post não encontrado")).when(postService).deletePost(1L);

        try {
            postController.deletePost(1L);
        } catch (Exception e) {
            assertEquals("Post não encontrado", e.getMessage());
        }

        verify(postService, times(1)).deletePost(1L);
    }

}
