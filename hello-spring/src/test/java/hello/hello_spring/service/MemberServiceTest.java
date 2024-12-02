package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    /**
     * 새로 생성된 레포지터리. MemberService 에서 생성한 레포지터리와 다르다는 문제점이 있다.
     * MemberService 에 의존성주입을 해준다.
     */

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    /**
     * 회원가입 테스트
     * 새로 생성한 회원을 저장한 후 ID로 조회하여 동일한지 검증
     */
    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

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
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /* try-catch를 이용한 검증
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */

    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}