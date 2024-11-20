package com.example.newsfeed_project.newsfeed.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class NewsfeedLike {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private long feedId;

  private long memberId;

}
