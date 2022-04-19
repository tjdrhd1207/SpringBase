package hello.hellospring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {       //멤버 객체의 핵심 비즈니스 로직만 들어있어야함, 엔티티는 최대한 순수하게 유지

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name="name")
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();




}
