package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // @AfterEach : 테스트가 끝날 때 마다 실행
    @AfterEach
    public void afterEach(){
        repository.clearStore(); // 한 메서드의 테스트가 끝날 때 마다 저장소는 초기화해주어야한다.
    }

    /**
     * 신규 회원 저장 테스트
     */
    @Test
    public void save(){
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        repository.save(member);

        /**
         * Optional 반환값 사용
         * 1. get()
         *  - 값을 그대로 반환해준다. NoSuchElementException을 반환할 가능성이 있다.
         *  - IfPresent() 로 값 체크없이 사용하면 안됨. 테스트케이스이기 떄문에 그냥 사용.
         *  - 어딘가에서 예외처리를 해주어야 한다.
         *
         * 2. orElse()
         *  - 값을 반환해주고 값이 없을 경우 매개변수로 지정해준 값을 반환한다.
         *
         * 3. orElseThrow()
         *  - 값이 있을 경우 값을 반환하고, 없을 경우 지정해준 예외(IllegalArgumentException)를 반환한다.
         *  - 어딘가에서 예외처리를 해주어야 한다.
         */

        //then
        Member result = repository.findById(member.getId()).get();

        //System.out.println("result = " + (result==member));   //<- 번거로움

        // org.junit.jupiter.api.Assertions 제공.
        //Assertions.assertEquals(member, result);  // 두 값이 일치하는가 확인. false 라면 오류발생
        //Assertions.assertEquals(member, null);

        assertThat(member).isEqualTo(result); // member 의 값이 result 값과 일치하는지.
    }


    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }


}
