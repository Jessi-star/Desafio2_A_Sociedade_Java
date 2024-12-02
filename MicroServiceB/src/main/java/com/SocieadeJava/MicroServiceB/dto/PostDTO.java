package com.SocieadeJava.MicroServiceB.dto;


import com.SocieadeJava.MicroServiceB.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Schema(description = "DTO to represent a post" )
public class PostDTO {
    @Schema(description = "Post Title", example = "My First Post" )
    private String title;

    @Schema(description = "Post Content", example ="This is the post content." )
    private String body;
    @Schema(description = "ID post (unique)", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
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
        return dto;
    }

}
