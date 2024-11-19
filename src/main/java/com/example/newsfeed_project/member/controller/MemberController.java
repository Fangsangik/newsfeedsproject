package com.example.newsfeed_project.member.controller;

import com.example.newsfeed_project.member.dto.MemberDto;
import com.example.newsfeed_project.member.dto.MemberUpdateResponseDto;
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
    public ResponseEntity<?> findById(@PathVariable Long id) {
        MemberDto memberById = memberService.getMemberById(id);
        return ResponseEntity.ok().body(memberById);
    }

    @PostMapping("/")
    public ResponseEntity<?> createMember(@RequestBody MemberDto memberDto) {
        MemberDto createdMember = memberService.createMember(memberDto);
        return ResponseEntity.ok().body(createdMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable Long id, @RequestBody MemberUpdateResponseDto memberUpdateResponseDto) {
        memberService.updateMember(memberUpdateResponseDto)
    }
}
