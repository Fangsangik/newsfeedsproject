package com.example.newsfeed_project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class IncorrectException extends RuntimeException {
  private final ErrorCode errorCode;
}