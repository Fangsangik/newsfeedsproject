package com.example.newsfeed_project.member.controller;

import com.example.newsfeed_project.member.dto.MemberDto;
import com.example.newsfeed_project.member.dto.MemberUpdateResponseDto;
import com.example.newsfeed_project.member.dto.ResponseDto;
import com.example.newsfeed_project.member.service.MemberServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberServiceImpl memberService;

    public MemberController(MemberServiceImpl memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<MemberDto>> findById(@PathVariable Long id) {
        MemberDto memberById = memberService.getMemberById(id);
        ResponseDto<MemberDto> response = new ResponseDto<>("회원 조회 성공", memberById);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDto<MemberDto>> createMember(@RequestBody MemberDto memberDto) {
        MemberDto createdMember = memberService.createMember(memberDto);
        ResponseDto<MemberDto> response = new ResponseDto<>("회원 생성 성공", createdMember);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable Long id, @RequestBody MemberDto memberDto) {
        MemberDto updateMember = memberService.updateMember(id, memberDto);
        MemberUpdateResponseDto responseDto = MemberUpdateResponseDto.toResponseDto(updateMember);
        ResponseDto<MemberUpdateResponseDto> response = new ResponseDto<>("회원 update 성공", responseDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/{email}")
    public ResponseEntity<ResponseDto<MemberDto>> findByEmail(@PathVariable String email) {
        MemberDto memberByEmail = memberService.getMemberByEmail(email);
        ResponseDto<MemberDto> response = new ResponseDto<>("회원을 성공적으로 찾았습니다.", memberByEmail);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> deleteMemberById(@PathVariable Long id) {
        memberService.deleteMemberById(id);
        ResponseDto<Void> response = new ResponseDto<>("회원 탈퇴 성공", null);
        return ResponseEntity.ok(response);
    }
}
