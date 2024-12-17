package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 스프링 통합 테스트
 */
@SpringBootTest // 테스트 시작할 때 Spring 실행, 끝나면 다운
@Transactional  // DB에서의 TRANSACTION으로 각 테스트마다 시작할 때 TRAN 생성하고 끝나면 ROLLBACK 하여 실제 DB에는 영향 X
class MemberServiceIntegrationTest {
    // 테스트인 경우에는 편하게 필드기반 Autowired 사용 가능
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    /**
     * 회원가입 테스트
     * 새로 생성한 회원을 저장한 후 ID로 조회하여 동일한지 검증
     */
    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("spring2");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    /**
     * 중복 회원 테스트
     * 이름이 같은 두 회원을 순차적으로 저장할 때 예외 발생이 정상적으로 동작하는지 검증
     */
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring3");

        Member member2 = new Member();
        member2.setName("spring3");

        //when
        memberService.join(member1);

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}