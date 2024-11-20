package com.example.newsfeed_project.comment.controller;

import com.example.newsfeed_project.comment.dto.CommentDto;
import com.example.newsfeed_project.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto dto) {
        CommentDto commentDto = commentService.createComment(dto);

        return new ResponseEntity<>(commentDto, HttpStatus.CREATED);
    }

}
