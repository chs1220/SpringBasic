package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {

    // ++) TDD (테스트 주도 개발) 란 테스트를 먼저 만든 이후 구현 클래스를 작성, 검증하는 방법이다.

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트는 메서드간 영향이 없게끔 설계 되어야 한다. 객체 생성, 공유되는 것 등은 비워주어야 한다
    @AfterEach // **** 각 테스트가 진행된 후 매번 작동하는 메서드 , 테스트를 위해 생성한 객체를 삭제한다 ****
    public void afterEach(){
        repository.clear();
    }


    @Test //잘 저장되고 잘 가져와지는지 테스트
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        // 테스트 체크 방법
        // 1. 찍어보기
        //    System.out.println("result = " + (result==member) );
        // 2. Assert 기능 : 기댓값, 실제값
        //    Assertions.assertEquals(member, result);
        // 3. 요즘 많이 쓰는 assertThat (org.assertj.core.api.Assertions )
        assertThat(result).isEqualTo(member);
    }


    @Test
    public void findByName() {
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
