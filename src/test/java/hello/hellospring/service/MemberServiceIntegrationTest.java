package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository ;


    @Test
    @Commit
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring123");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, ()-> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
    
    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers(){
            long start = System.currentTimeMillis();
        try{
            return memberRepository.findAll();
        }finally{
            long finish = System.currentTimeMillis();

            long timeMs = finish - start ;
            System.out.println("findMembers" + timeMs + "ms");

        }
    }


 /*   @Test
    public void 회원조회(){

        Optional<String> member1 = memberService.findOne(Long.valueOf(22)).map(Member::getName);



        System.out.println("name"+member1);
    }*/

}