package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional // 데이터를 저장하거나 변경할 때 항상 필요
public class MemberService {

    private final MemberRepository memberRepository;

    // 의존성 주입
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원가입
     */
    public long join(Member member) {
        // 이름이 같으면 중복 회원
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);  // 검증 통과 시 저장
        return member.getId();

        /*

        long start = System.currentTimeMillis();

        try {
            // 이름이 같으면 중복 회원
            validateDuplicateMember(member); // 중복회원 검증
            memberRepository.save(member);  // 검증 통과 시 저장

            return member.getId();

        } finally {

            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
         */

    }

    private void validateDuplicateMember(Member member) {
        // 예외처리시 이전에는 if 문으로 null 값인지 확인했지만, 옵셔널 사용해서 넘겨받았으므로 ifPresent 메서드 이용
        memberRepository.findByName(member.getName())   // findByName 반환 값 optional member 객체
                .ifPresent(m -> {               // 값이 존재하는지 확인 후 있다면 중복이기 때문에 예외처리
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
        /*
        long start = System.currentTimeMillis();

        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }
        */
    }

    /**
     * ID로 회원찾기
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}