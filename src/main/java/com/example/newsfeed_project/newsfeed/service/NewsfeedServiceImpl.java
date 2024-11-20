package com.example.newsfeed_project.newsfeed.service;

import static com.example.newsfeed_project.exception.ErrorCode.NO_AUTHOR;

import com.example.newsfeed_project.exception.AuthorException;
import com.example.newsfeed_project.member.entity.Member;
import com.example.newsfeed_project.member.service.MemberService;
import com.example.newsfeed_project.newsfeed.dto.NewsfeedRequestDto;
import com.example.newsfeed_project.newsfeed.dto.NewsfeedResponseDto;
import com.example.newsfeed_project.newsfeed.entity.Newsfeed;
import com.example.newsfeed_project.newsfeed.repository.NewsfeedRepository;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NewsfeedServiceImpl implements NewsfeedService{

  private final NewsfeedRepository newsfeedRepository;
  private final MemberService memberService;

  @Override
  public NewsfeedResponseDto save(NewsfeedRequestDto dto, HttpSession session) {
    String email = (String) session.getAttribute("email");
    Member member = memberService.validationId(email);
    Newsfeed newsfeed = new Newsfeed(dto.getImage(), dto.getTitle(), dto.getContent());
    newsfeed.setMember(member);
    newsfeedRepository.save(newsfeed);
    return null;
  }

  @Override
  public List<NewsfeedResponseDto> findAll(Pageable pageable) {
    return newsfeedRepository.findAll(pageable)
        .stream()
        .map(NewsfeedResponseDto::toDto)
        .toList();
  }

  @Transactional
  @Override
  public NewsfeedResponseDto updateNewsfeed(Long id, NewsfeedRequestDto dto, HttpSession session) {
    String email = (String) session.getAttribute("email");
    Newsfeed newsfeed = findNewsfeedByIdOrElseThrow(id);
    checkEmail(email, newsfeed);
    newsfeed.updateNewsfeed(dto);
    return new NewsfeedResponseDto(newsfeed.getId(), newsfeed.getFeedImage(), newsfeed.getContent(), newsfeed.getUpdatedAt);
  }

  @Transactional
  @Override
  public void delete(Long id, HttpSession session) {
    String email = (String) session.getAttribute("email");
    Newsfeed newsfeed = findNewsfeedByIdOrElseThrow(id);
    checkEmail(email, newsfeed);
    newsfeedRepository.delete(newsfeed);
  }

  private Newsfeed findNewsfeedByIdOrElseThrow(Long id) {
    return newsfeedRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("뉴스피드 아이디가 없습니다."));
  }

  private void checkEmail(String email, Newsfeed newsfeed) {
    if(!newsfeed.getMember().getEmail().equals(email)) {
      throw new AuthorException(NO_AUTHOR);
    }
  }

}
