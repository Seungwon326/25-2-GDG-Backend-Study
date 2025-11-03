package com.example.shop.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Member findById(Long id) {
        return em.find(Member.class, id); // JPA가 알아서 맴버 객체로 전환
    }

    public List<Member> findAll() {
        return em.createQuery("SELECT m FROM Member m", Member.class)
                .getResultList();
    }

    public Member findByLoginId(String loginId) {
        List<Member> result = em.createQuery(
                "SELECT m FROM Member m WHERE m.loginId = :loginId", Member.class
        ).setParmeter("loginId", loginId).getResultList();

        return result.isEmpty() ? null : result.get(0); // 리스트가 비어있으면 null, 회원이 있으면 정보 반환
    }

    public void save(Member member) {
        em.persist(member);
    }

    public void deleteById(Long id) {
        Member member = em.find(Member.class, id);
        em.remove(member);
    }
}
