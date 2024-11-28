package com.SocieadeJava.MicroServiceB.dto;

import com.SocieadeJava.MicroServiceB.entity.Comment;
import com.SocieadeJava.MicroServiceB.service.CommentService;
import lombok.*;

@Data @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CommentDTO {
    private String body;
    private Long id;

    public CommentDTO(Comment comment){
        this.id = comment.getId();
        this.body = comment.getTexto();
    }

    public static CommentDTO fromEntity(Comment comment){
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        return dto;
    }
}
