package com.example.newsfeed_project.member.service;

import com.example.newsfeed_project.member.dto.MemberDto;

public interface MemberService {
    MemberDto createMember(MemberDto memberDto);
    MemberDto updateMember(Long id, String password, MemberDto memberDto);
    MemberDto getMemberById(Long id);
    MemberDto getMemberByEmail(String email);
    void deleteMemberById(Long id);
    MemberDto changePassword(Long id, String oldPassword, String newPassword);

    boolean authenticate(String email, String password);
}
