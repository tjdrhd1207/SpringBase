package hello.hellospring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member {       //멤버 객체의 핵심 비즈니스 로직만 들어있어야함, 엔티티는 최대한 순수하게 유지

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name="name")
    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    @JsonIgnore //api에서 회원정보를 조회할 때 회원의 주문정보까지 같이 조회가되기 때문에, 순수한 회원의 정보만 조회되게 하기 위해
                //하지만 엔티티안에 JsonIgnore를 쓰기 시작하면 나중에 관리할 때 일관성이 사라짐
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();




}
