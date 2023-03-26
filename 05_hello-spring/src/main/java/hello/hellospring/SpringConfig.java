package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.domain.MemoryMemberRepository;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.Time;

@Configuration
public class SpringConfig {

  private final MemberRepository memberRepository;

  public SpringConfig(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }
  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository);
  }

//  @Bean
//  public TimeTraceAop timeTraceAop(){
//    return new TimeTraceAop();
//}


//  private final DataSource dataSource;
//  private final EntityManager em;
//  public SpringConfig(DataSource dataSource, EntityManager em) {
//    this.dataSource = dataSource;
//    this.em = em;
//  }
//
//  @Bean
//    public MemberService memberService(){
//      return new MemberService(memberRepository());
//  }
//
//  @Bean
//  public MemberRepository memberRepository() {
//// return new MemoryMemberRepository();
//// return new JdbcMemberRepository(dataSource);
//    return new JdbcTemplateMemberRepository(dataSource);
//  }
}

