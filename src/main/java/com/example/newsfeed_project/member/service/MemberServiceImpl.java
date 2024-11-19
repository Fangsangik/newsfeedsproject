package com.example.newsfeed_project.member.service;

import com.example.newsfeed_project.member.dto.MemberDto;
import com.example.newsfeed_project.member.entity.Member;
import com.example.newsfeed_project.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberDto createMember(MemberDto memberDto) {
        Member createMember = new Member();
        Member save = memberRepository.save(createMember);
        return new MemberDto(save);
    }

    @Override
    public MemberDto updateMember(Long id, MemberDto memberDto) {
        Member findMemberId = validateId(id);

        findMemberId.updatedMember(memberDto);
        Member updatedMember = memberRepository.save(findMemberId);
        return new MemberDto(updatedMember);

    }

    @Override
    public MemberDto getMemberById(Long id) {
        Member findMemberId = validateId(id);
        return new MemberDto(findMemberId);
    }

    @Override
    public MemberDto getMemberByEmail(String email) {
        Member findByEmail = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Member email not found"));

        return new MemberDto(findByEmail);
    }

    @Override
    public void deleteMemberById(Long id) {
        validateId(id);
        memberRepository.deleteById(id);
    }

    private Member validateId(Long id) {
        Member findMemberId = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        return findMemberId;
    }
}
