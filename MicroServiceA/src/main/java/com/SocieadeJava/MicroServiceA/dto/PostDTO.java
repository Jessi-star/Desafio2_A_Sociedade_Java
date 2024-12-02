package com.SocieadeJava.MicroServiceA.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    @Positive(message = "O ID deve ser um número positivo.")
    private Long id;

    @NotBlank(message = "O título é obrigatório.")
    @Size(min = 5, max = 100, message = "O título deve ter entre 5 e 100 caracteres.")
    private String title;

    @NotBlank(message = "O corpo do post é obrigatório.")
    @Size(min = 10, message = "O corpo deve ter pelo menos 10 caracteres.")
    private String body;
}
