package hello.hellospring.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {       //화면을 처리하기 위한 로직이 있는 DTO, FORM 객체

    @NotEmpty(message="회원 이름은 필수 이름이다.")
    private String name;

    @NotEmpty(message="사는 도시를 입력해주세요.")
    private String city;
    private String street;
    private String zipcode;

}
