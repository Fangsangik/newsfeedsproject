package com.example.newsfeed_project.newsfeed.dto;

import com.example.newsfeed_project.newsfeed.entity.Newsfeed;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class NewsfeedResponseDto {

  private String image;

  private String title;

  private String content;

  private LocalDateTime updatedAt;

  public static NewsfeedResponseDto toDto(Newsfeed newsfeed) {
    return new NewsfeedResponseDto(
        newsfeed.getFeedImage(),
        newsfeed.getTitle(),
        newsfeed.getContent(),
        newsfeed.getUpdatedAt()
    );
  }

}
