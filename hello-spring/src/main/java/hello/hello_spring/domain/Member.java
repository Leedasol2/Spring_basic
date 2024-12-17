package hello.hello_spring.domain;

import jakarta.persistence.*;

@Entity
public class Member {

    // 회원 정보
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // 일반 컬럼일 때 , @Column(name = "username")

    public Long getId() {
        return id;
    }

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
