package com.example.newsfeed_project.member.entity;

import com.example.newsfeed_project.member.dto.MemberDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private int age;
    private String image;

    private LocalDateTime deletedAt;

    public static Member toEntity(MemberDto memberDtO) {
        return Member.builder()
                .id(memberDtO.getId())
                .name(memberDtO.getName())
                .email(memberDtO.getEmail())
                .password(memberDtO.getPassword())
                .phoneNumber(memberDtO.getPhoneNumber())
                .address(memberDtO.getAddress())
                .age(memberDtO.getAge())
                .image(memberDtO.getImage())
                .deletedAt(memberDtO.getDeletedAt())
                .build();
    }

    public void updatedMember(MemberDto memberDtO) {
        if (memberDtO.getName() != null) {
            this.name = memberDtO.getName();
        }

        if (memberDtO.getImage() != null) {
            this.image = memberDtO.getImage();
        }

        if (memberDtO.getPhoneNumber() != null) {
            this.phoneNumber = memberDtO.getPhoneNumber();
        }

        if (memberDtO.getAddress() != null) {
            this.address = memberDtO.getAddress();
        }
    }
}

