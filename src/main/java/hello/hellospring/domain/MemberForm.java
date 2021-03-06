package hello.hellospring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class MemberForm {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="name")
    private String name;


}
