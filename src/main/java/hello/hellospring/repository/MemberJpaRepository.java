package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.Iterator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository {

    @Autowired
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public void saveMembers(List<Member> memberList){
        for(Iterator<Member> it = memberList.iterator(); it.hasNext();){
            Member member = it.next();

            em.persist(member);
        }
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }

}
