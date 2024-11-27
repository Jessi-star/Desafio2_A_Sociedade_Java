package com.SocieadeJava.MicroServiceB.dto;


import com.SocieadeJava.MicroServiceB.entity.Post;
import lombok.Data;
import lombok.*;

@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PostDTO {
    private String title;
    private String body;
    private Long Id;


    public PostDTO(Post post) {
        this.title = post.getTitulo();
        this.body = post.getConteudo();
    }

    public static PostDTO fromEntity(Post post) {
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitulo());
        return dto;
    }

}
