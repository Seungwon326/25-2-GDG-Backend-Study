package com.example.shop.member;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="member_id")
    private Long id;

    @Column(name = "member_login_id" ,length = 50)
    private String loginId;

    // 비밀번호
    @Column(name = "member_pw", length=100)
    private String password;

    // 전화번호
    @Column(name = "member_phone",length=50)
    private String phoneNumber;

    // 주소
    @Column(name = "member_address",length=20)
    private String address;

    // 적립금
    @Column(name = "member_point")
    private int point;

    public Member(String loginId, String password, String phoneNumber, String address) {
        this.loginId = loginId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.point = 0; // 신규 회원 적립금은 0으로 초기화
    }

    // 회원 정보 수정 메서드(loginId는 변경 불가)
    public void updateInfo(String password, String phoneNumber, String address) {
        if (password != null) {
            this.password = password;
        }
        if (phoneNumber != null) {
            this.phoneNumber = phoneNumber;
        }
        if (address != null) {
            this.address = address;
        }
    }
}