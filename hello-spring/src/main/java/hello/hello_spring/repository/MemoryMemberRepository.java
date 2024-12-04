package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
// MemberRepository 인터페이스의 임시 구현체
//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // id는 시스템에서 정하도록 세팅

    // 회원 정보 저장
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    // ID로 회원 정보 조회
    @Override
    public Optional<Member> findById(Long id) {
        // Optional을 사용하여 반환값이 Null 인 경우에도 NPE이 발생하지 않고 클라이언트에서 작업가능
        // ofNullable : 값이 있을 수도 있고 null 일 수도 있는 경우 사용.
        return Optional.ofNullable(store.get(id));
    }

    // 이름으로 회원 정보 조회
    @Override
    public Optional<Member> findByName(String name) {
        // store 에 저장된 모든 정보에서 회원 이름과 동일한 회원정보 추출
        return store.values().stream() // 람다를 활용해 배열을 루프를 돌림
                .filter(member -> member.getName().equals(name)) // 파라미터의 이름과 동일하면 필터
                .findAny(); // 가장 먼저 찾은 요소 반환 (저장 순서에 관계없이 반환)
    }

    // 저장된 모든 회원정보 반환
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 저장된 모든 회원정보 제거
    public void clearStore(){
        store.clear();
    }
}
