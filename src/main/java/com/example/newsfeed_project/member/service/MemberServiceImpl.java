package com.example.newsfeed_project.member.service;

import com.example.newsfeed_project.config.PasswordEncoder;
import com.example.newsfeed_project.member.dto.MemberDto;
import com.example.newsfeed_project.member.entity.Member;
import com.example.newsfeed_project.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MemberDto createMember(MemberDto memberDto) {
        Optional<Member> existingMember = memberRepository.findByEmail(memberDto.getEmail());
        if (existingMember.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        memberDto = encryptedPassword(memberDto);
        Member createMember = Member.toEntity(memberDto);
        Member save = memberRepository.save(createMember);
        return MemberDto.toDto(save);
    }

    @Override
    public MemberDto updateMember(Long id, String password, MemberDto memberDto) {
        Member findMemberId = validateId(id);
        if (!passwordEncoder.matches(password, findMemberId.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        findMemberId.updatedMember(memberDto);
        Member updatedMember = memberRepository.save(findMemberId);
        return MemberDto.toDto(updatedMember);

    }

    @Override
    public MemberDto getMemberById(Long id) {
        Member findMemberId = validateId(id);
        return MemberDto.toDto(findMemberId);
    }

    @Override
    public MemberDto getMemberByEmail(String email) {
        Member findByEmail = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Member email not found"));

        return MemberDto.toDto(findByEmail);
    }

    @Override
    public void deleteMemberById(Long id) {
        validateId(id);
        memberRepository.deleteById(id);
    }

    @Override
    public MemberDto changePassword(Long id, String oldPassword, String newPassword) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        if (!passwordEncoder.matches(oldPassword, member.getPassword())) {
            throw new IllegalArgumentException("Old password and new password do not match");
        }

        String encodedPassword = passwordEncoder.encode(newPassword);
        Member changePassword = member.withPassword(encodedPassword);
        changePassword = memberRepository.save(changePassword);
        return MemberDto.toDto(changePassword);
    }

    @Override
    public boolean authenticate(String email, String password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Member email not found"));

        if (member != null && passwordEncoder.matches(password, member.getPassword())) {
            return true;
        }

        return false;
    }


    private Member validateId(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }

    private MemberDto encryptedPassword(MemberDto memberDto) {
        if (memberDto.getPassword() != null && !memberDto.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(memberDto.getPassword());
            memberDto = memberDto.withPassword(encodedPassword);
        }
        return memberDto;
    }
}
