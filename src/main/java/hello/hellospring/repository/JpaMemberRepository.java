package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{


    // jpa를 관리하는 EntityManager
    // jpa 라이브러리 추가하면 스프링부트에서 entitymanager가 생성됨 -> 리포지토리에 인젝션
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // em이 쿼리문 작성, id와 필드변수 매핑까지 전부 다 해줌
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id) ;
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // pk 가 아닌 조건이 필요한 쿼리는 직접 sql문을 작성해주어야 하고 이를 jpql문이라고 부름
        // 그런데, 데이터 jpa를 활용하면 이 쿼리문도 생략 가능
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from member m", Member.class)
                .getResultList();

    }
}
