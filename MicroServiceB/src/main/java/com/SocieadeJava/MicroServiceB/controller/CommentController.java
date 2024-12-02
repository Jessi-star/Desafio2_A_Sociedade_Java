package com.SocieadeJava.MicroServiceB.controller;

import com.SocieadeJava.MicroServiceB.dto.CommentDTO;
import com.SocieadeJava.MicroServiceB.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDTO createComment(@PathVariable Long postId, @RequestBody CommentDTO commentDTO) {
        return commentService.createComment(postId, commentDTO);
    }
}
