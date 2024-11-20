package com.example.newsfeed_project.newsfeed.service;

import com.example.newsfeed_project.newsfeed.dto.NewsfeedRequestDto;
import com.example.newsfeed_project.newsfeed.dto.NewsfeedResponseDto;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface NewsfeedService {

  //뉴스피드 생성
  NewsfeedResponseDto save(NewsfeedRequestDto dto, HttpSession request);

  List<NewsfeedResponseDto> findAll(Pageable pageable);

  NewsfeedResponseDto updateNewsfeed(Long id, NewsfeedRequestDto dto, HttpSession session);

  void delete(Long id, HttpSession session);
}
