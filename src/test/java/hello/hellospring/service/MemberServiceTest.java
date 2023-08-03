package hello.hellospring.service;

import hello.hellospring.domain.Member;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    //자동으로 테스트 만들기 : ctrl + shift + T

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    // MemoryMemberRepository memberRepository =   new MemoryMemberRepository();
    // MemberService 의 memberRepository와  Test 에서의 memberRepository가 각각 다른 인스턴스인데... 통일 시키는게 좋다
    // MemberService 의 memberRepository를 생성자를 만들어 외부에서 인스턴스 생성하게 변경

    @BeforeEach
    public void beforEach() { // 각각 테스트는 독립적으로 실행되어야 하기 때문에 매번 객체 생성
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clear();
    }


    @Test
    void 회원가입() {  //테스트 코드는 과감하게 한글로 작성해도 된다(실제 코드에 포함 x)
        //given : 이런 상황이 주어졌을 때 (회원가입 상황)
        Member member = new Member();
        member.setName("spring");

        //when  : 이걸 실행했을때 (join 메서드를 실행)
        Long saveId = memberService.join(member);

        //then  : 이런 결과가 나와야해 ()
        Member findMember = memberService.findOne(saveId).get();
        assertThat(findMember.getName()).isEqualTo(member.getName());

    } // 이건 반쪽짜리 test  ->  예외가 터지는 상황을 검증 할 수 있어야 한다.

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when : 중복 이름 가입 검증

        memberService.join(member1);
        // 오른쪽 람다식의 로직을 실행할 때 IllegalStateException.class 이 예외가 터져야 한다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


/*      try-catch 방법
        memberService.join(member1);
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/


        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}