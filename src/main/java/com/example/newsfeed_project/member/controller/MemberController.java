package com.example.newsfeed_project.member.controller;

import com.example.newsfeed_project.member.dto.MemberDto;
import com.example.newsfeed_project.member.dto.MemberUpdateResponseDto;
import com.example.newsfeed_project.member.dto.PasswordRequestDto;
import com.example.newsfeed_project.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        MemberDto memberById = memberService.getMemberById(id);
        return ResponseEntity.status(HttpStatus.OK).body(memberById);
    }

    @PostMapping("/register")  // POST /members 경로
    public ResponseEntity<?> createMember(@RequestBody MemberDto memberDto) {
        MemberDto createdMember = memberService.createMember(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable Long id, @RequestBody MemberDto memberDto) {
        MemberDto updateMember = memberService.updateMember(id, memberDto.getPassword(), memberDto);
        MemberUpdateResponseDto responseDto = MemberUpdateResponseDto.toResponseDto(updateMember);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/email")
    public ResponseEntity<?> findByEmail(@RequestParam String email) {
        MemberDto memberByEmail = memberService.getMemberByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(memberByEmail);
    }

    @PostMapping("/password/{id}")
    public ResponseEntity<?> changePassword(@RequestBody PasswordRequestDto passwordRequestDto) {
        MemberDto memberDto = memberService.changePassword(passwordRequestDto.getId(), passwordRequestDto.getOldPassword(), passwordRequestDto.getNewPassword());
        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMemberById(@PathVariable Long id) {
        memberService.deleteMemberById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
