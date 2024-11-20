package com.example.newsfeed_project.comment.service;

import com.example.newsfeed_project.comment.dto.CommentDto;
import com.example.newsfeed_project.comment.entity.Comment;
import com.example.newsfeed_project.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private CommentRepository commentRepository;


    public CommentDto createComment(CommentDto dto) {
        Comment createComment = Comment.toEntity(dto);
        Comment save = commentRepository.save(createComment);

        return CommentDto.toDto(save);
    }
}
