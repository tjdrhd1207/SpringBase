package hello.hellospring.controller;

import hello.hellospring.domain.Address;
import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberJpaService;
import hello.hellospring.validation.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberJpaController {

    private final MemberJpaService memberJpaService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm2";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result){

        if(result.hasErrors()){
            return "members/createMemberForm2";
        }


        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberJpaService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){

        /*
        List<Member> members = memberJpaService.findMembers();
        model.addAttribute("members",members);
        -> 밑에 한줄로 메소드 리팩토리 가능 */

        model.addAttribute("members", memberJpaService.findMembers());


        return "members/memberList";
    }


    /*@InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(new MemberValidator());
    }*/

}
