package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository< 매핑할 객체 타입, pk 타입 >
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);

    // 1. spring data 에서 기본적인 crud 쿼리를 제공한다 (save, findOne, exist 등)
    // 2. 이름을 규칙대로 지으면 자동으로 쿼리문 제공한다
    //     ex) findByName : "name"으로 조건추가 => select m from Member m where m.name = ?
    // 3. 페이징 기능 자동 제공

    // 실무에서는 jpa + spring data jpa + Querydsl 기술들을 섞어서 사용

}
