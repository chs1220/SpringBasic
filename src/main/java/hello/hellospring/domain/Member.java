package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {

    private Long id;
    private String name;

    @Id  // pk 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 알아서 pk값 생성 -> identity 전략
    public Long getId() {
        return id;
    }

    @Column(name = "name") // 테이블 컬럼명과 필드변수명이 다르면 이 방법으로 매핑
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
