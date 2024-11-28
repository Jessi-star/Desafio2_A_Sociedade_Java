package com.SocieadeJava.MicroServiceA.dto;

import lombok.*;

@Data @Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class PostDTO {
    private Long id;
    private String title;
    private String body;
}
