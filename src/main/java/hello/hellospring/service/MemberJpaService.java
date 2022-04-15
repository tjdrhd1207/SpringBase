package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberJpaRepository;
import hello.hellospring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor    //final 변수를 가진 필드의 생성자를 명시
public class MemberJpaService {

    private final MemberJpaRepository memberJpaRepository;

    
    /*
    * 회원 가입
    * */
    @Transactional
    public Long join(Member member){

        validateDuplicateMember(member);        //중복 회원 검증
        memberJpaRepository.save(member);
        return member.getId();
    }
    
    //회원 전체 조회
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberJpaRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회

    public List<Member> findMembers(){
        return memberJpaRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberJpaRepository.findOne(memberId);
    }
}
