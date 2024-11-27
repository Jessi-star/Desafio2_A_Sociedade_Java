package com.SocieadeJava.MicroServiceB.service;

import com.SocieadeJava.MicroServiceB.entity.Comment;
import com.SocieadeJava.MicroServiceB.repository.CommentRepository;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> findCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
