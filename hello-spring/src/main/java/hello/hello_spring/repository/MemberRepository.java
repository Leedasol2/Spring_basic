package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원 정보 저장
    Optional<Member> findById(Long id); // 아이디로 회원 조회
    Optional<Member> findByName(String name); // 이름으로 회원 조회
    List<Member> findAll();     // 모든 회원 조회
}
