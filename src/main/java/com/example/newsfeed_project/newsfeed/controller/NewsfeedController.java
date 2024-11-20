package com.example.newsfeed_project.newsfeed.controller;

import com.example.newsfeed_project.newsfeed.dto.NewsfeedRequestDto;
import com.example.newsfeed_project.newsfeed.dto.NewsfeedResponseDto;
import com.example.newsfeed_project.newsfeed.service.NewsfeedService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/newsfeeds")
@RequiredArgsConstructor
public class NewsfeedController {

  private final NewsfeedService newsfeedService;

  @PostMapping
  public ResponseEntity<NewsfeedResponseDto> save(
      @Valid @RequestBody NewsfeedRequestDto newsfeedRequestDto,
      HttpServletRequest request
  ) {
    HttpSession session = request.getSession(false);
    NewsfeedResponseDto newsfeedResponseDto = newsfeedService.save(newsfeedRequestDto, session);
    return new ResponseEntity<>(newsfeedResponseDto, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<NewsfeedResponseDto>> findAll(
      @PageableDefault(size = 10, sort = "updateAt", direction = Direction.DESC)
      Pageable pageable
  ){
    List<NewsfeedResponseDto> list = newsfeedService.findAll(pageable);
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<NewsfeedResponseDto> updateNewsfeed(
      @PathVariable Long id,
      @Valid @RequestBody NewsfeedRequestDto newsfeedRequestDto,
      HttpServletRequest request
  ){
    HttpSession session = request.getSession(false);
    NewsfeedResponseDto newsfeedResponseDto = newsfeedService.updateNewsfeed(id, newsfeedRequestDto, session);
    return new ResponseEntity<>(newsfeedResponseDto, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteNewsfeed(
      @PathVariable Long id,
      HttpServletRequest request
  ){
    HttpSession session = request.getSession(false);
    newsfeedService.delete(id, session);
    return new ResponseEntity<>("Deleted", HttpStatus.OK);
  }

}
