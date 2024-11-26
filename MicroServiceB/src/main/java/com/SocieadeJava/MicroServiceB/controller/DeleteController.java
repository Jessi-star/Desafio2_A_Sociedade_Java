package com.SocieadeJava.MicroServiceB.controller;

import com.SocieadeJava.MicroServiceB.exceptions.ResourceInUseException;
import com.SocieadeJava.MicroServiceB.exceptions.ResourceNotFoundException;
import com.SocieadeJava.MicroServiceB.service.CommentService;
import com.SocieadeJava.MicroServiceB.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")

public class DeleteController {
    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPost(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ResourceInUseException.class)
    public ResponseEntity<String> handleResourceInUse(ResourceInUseException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
