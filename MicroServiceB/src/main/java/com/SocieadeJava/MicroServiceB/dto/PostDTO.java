package com.SocieadeJava.MicroServiceB.dto;

import com.SocieadeJava.MicroServiceB.entity.Post;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    @JsonProperty("title")
    private String title;

    @JsonProperty("body")
    private String body;

    private Long id;

    public PostDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitulo();
        this.body = post.getConteudo();
    }

    public static PostDTO fromEntity(Post post) {
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitulo());
        dto.setBody(post.getConteudo());
        return dto;
    }
}
