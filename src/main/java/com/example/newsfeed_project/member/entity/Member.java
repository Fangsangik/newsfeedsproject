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

    public Member(MemberDto memberDtO) {
        this.id = memberDtO.getId();
        this.name = memberDtO.getName();
        this.email = memberDtO.getEmail();
        this.password = memberDtO.getPassword();
        this.phoneNumber = memberDtO.getPhoneNumber();
        this.address = memberDtO.getAddress();
        this.age = memberDtO.getAge();
        this.image = memberDtO.getImage();
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

