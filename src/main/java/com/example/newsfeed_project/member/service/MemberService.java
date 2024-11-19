package com.example.newsfeed_project.member.service;

import com.example.newsfeed_project.member.dto.MemberDto;

public interface MemberService {
    MemberDto createMember(MemberDto memberDto);
    MemberDto updateMember(Long id, MemberDto memberDto);
    MemberDto getMemberById(Long id);
    MemberDto getMemberByEmail(String email);
    void deleteMemberById(Long id);
}
