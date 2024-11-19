package com.example.newsfeed_project.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateResponseDto {
    private String image;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;


    public static MemberUpdateResponseDto toResponseDto(MemberDto memberDto) {
        return MemberUpdateResponseDto.builder()
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .password(memberDto.getPassword())
                .phoneNumber(memberDto.getPhoneNumber())
                .address(memberDto.getAddress())
                .image(memberDto.getImage())
                .build();
    }
}
