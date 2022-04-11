package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.memberUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepository2Test {

    @Autowired
    MemberRepository2 memberRepository2;

     @Test
     @Rollback(false)
    public void testMember() throws Exception{
         //given
         Member member = new Member();
         member.setName("memberA");

         //when
         Long saveId = memberRepository2.save(member);
         Member findMember = memberRepository2.find(saveId);

         //then
         Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
         Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
         Assertions.assertThat(findMember).isEqualTo(member);


     }
     @Test
     public void testUser() throws  Exception{
         //given
         memberUser user = new memberUser();
         user.setUsername("memberA");

         //when
         Long saveId = memberRepository2.save(user);
         //User findUser = memberRepository2.findUser(saveId);

         //then
         //Assertions.assertThat(findUser.getId()).isEqualTo(user.getId());
         //Assertions.assertThat(findUser.getUsername()).isEqualTo(user.getUsername());
     }

}