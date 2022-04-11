package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.memberUser;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository2 {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }

    public Long save(memberUser user){
        System.out.println("user정보 :"+user.getUsername());
        em.persist(user);
        return user.getId();
    }

    public memberUser findUser(Long id){
        return em.find(memberUser.class, id);
    }

}
