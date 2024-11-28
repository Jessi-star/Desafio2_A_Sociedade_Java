package com.SocieadeJava.MicroServiceB.service;

import com.SocieadeJava.MicroServiceB.dto.CommentDTO;
import com.SocieadeJava.MicroServiceB.entity.Comment;
import com.SocieadeJava.MicroServiceB.entity.Post;
import com.SocieadeJava.MicroServiceB.exceptions.ResourceNotFoundException;
import com.SocieadeJava.MicroServiceB.repository.CommentRepository;
import com.SocieadeJava.MicroServiceB.repository.PostRepository;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentDTO createComment(Long postId, CommentDTO commentDTO){
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post não encontrado com ID: " + postId));

        Comment comment = new Comment();
        comment.setTexto(commentDTO.getBody());
        comment.setPost(post);

        comment = commentRepository.save(comment);

        return CommentDTO.fromEntity(comment);
    }

}
