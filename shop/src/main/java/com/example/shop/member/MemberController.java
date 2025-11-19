package com.example.shop.member;


import com.example.shop.member.dto.MemberCreateRequest;
import com.example.shop.member.dto.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberSerivce;

    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberCreateRequest request){
        Long memberId = memberSerivce.createMember(request);
        return ResponseEntity.created(URI.create("/members"+ memberId)).build();
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers(){
        List<Member> members = memberSerivce.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getMember(@PathVariable Long memberId) {
        //Service 계층에서 memberId 하나 가져옴
        Member member = memberSerivce.getMemberById(memberId);
        return ResponseEntity.ok(member);
    }


    @PatchMapping("/{memberId}")
    public ResponseEntity<Void> updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberUpdateRequest request){
        memberSerivce.updateMember(memberId,request);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId){
        memberSerivce.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }


}