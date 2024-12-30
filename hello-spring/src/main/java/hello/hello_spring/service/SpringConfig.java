package hello.hello_spring.service;

import hello.hello_spring.aop.TimeTraceAop;
import hello.hello_spring.repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
    //private final DataSource dataSource;
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
     */

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository); // 스프링 빈에 등록된 memberRepository()를 넣어준다.
    }

    /*
    // AOP 메서드 빈 등록. AOP 메서드에 컴포넌트를 달아 사용해도 된다.
    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
    */

/*
    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource); // 순수 JDBC 설정
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    }
    */
}
