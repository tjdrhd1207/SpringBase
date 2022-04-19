package hello.hellospring.validation;


import hello.hellospring.controller.MemberForm;
import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberJpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class MemberValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return MemberForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberForm member = (MemberForm) target;

        String mName = member.getName();

        if(null == mName || mName.trim().isEmpty()){
            System.out.println("회원이름을 입력하세요");
            //errors.rejectValue("name","name");

        }

        String mCity = member.getCity();

        if(null == mCity || mCity.trim().isEmpty()){
            System.out.println("도시를 입력해주세요");
            //errors.rejectValue("city","city");
        }


    }
}
