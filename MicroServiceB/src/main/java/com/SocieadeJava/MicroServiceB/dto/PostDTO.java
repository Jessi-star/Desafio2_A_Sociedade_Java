package com.SocieadeJava.MicroServiceB.dto;


import com.SocieadeJava.MicroServiceB.entity.Post;
import lombok.Data;
import lombok.*;

@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PostDTO {
    private String title;
    private String body;

    public PostDTO(Post post) {
        this.title = post.getTitulo();
        this.body = post.getConteudo();
    }
}
