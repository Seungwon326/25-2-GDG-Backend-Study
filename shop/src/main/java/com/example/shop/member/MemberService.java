package com.example.shop.member;


import com.example.shop.member.dto.MemberCreateRequest;
import com.example.shop.member.dto.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long createMember(MemberCreateRequest request) { // 회원 등록
        Member existingMember = memberRepository.findByLoginId(request.getLoginId());
        if (existingMember != null) {
            throw new RuntimeException("이미 존재하는 로그인 id입니다: " +  request.getLoginId());
        }

        Member member = new Member(
                request.getLoginId(),
                request.getPassword(),
                request.getPhoneNumber(),
                request.getAddress()
        );

        memberRepository.save(member);

        return member.getId();
    }

    @Transactional(readOnly = true) // 전체 회원 목록 조회
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true) // 개별 회원 정보 상세 조회
    public Member getMemberById(Long id) {
        Member member = memberRepository.findById(id);

        if (member == null) {
            throw new RuntimeException("회원을 찾을 수 없습니다. ");
        }

        return member;
    }

    @Transactional
    public void updateMember(Long id, MemberUpdateRequest request) { // 회원 정보 수정
        Member member = memberRepository.findById(id);

        if (member == null) {
            throw new RuntimeException("회원을 찾을 수 없습니다. ");
        }

        // 회원 정보 수정(도메인 객체의 메서드 사용)
        member.updateInfo(request.getPassword(), request.getPhoneNumber(), request.getAddress());
    }

    @Transactional
    public void deleteMember(Long id) { // 회원 삭제
        Member member = memberRepository.findById(id);

        if (member == null) {
            throw new RuntimeException("회원을 찾을 수 없습니다. ");
        }

        // Repository를 통해 삭제
        memberRepository.deleteById(id);
    }
}