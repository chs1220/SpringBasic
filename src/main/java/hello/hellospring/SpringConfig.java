package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

/*  JDBC에 사용되는 DataSource
    private final DataSource dataSource;
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
*/

/*  jpa 에 사용되는 em
    private final EntityManager em;
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
*/

    private final MemberRepository memberRepository;

    // spring data jpa 방법
    // 스프링에서 자동으로 MemberRepository를 상속받은 SpringDataJpaMemberRepository 인터페이스의 구현체를 생성해서 스프링 빈에 등록함
    // 그걸 인젝션 -> memberservice에도 주입
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    /*
    @Bean
    public MemberRepository memberRepository() {

        // return new MemoryMemberRepository();
        // 인터페이스-다형성으로 다른 코드 수정없이 db연결이 가능하다

        // return new JdbcMemberRepository(dataSource);       // 순수 jdbc
        // return new JdbcTemplateMemberRepository(dataSource);    // jdbc 템플릿
        return new JpaMemberRepository(em);    // jpa
    }
     */


}
