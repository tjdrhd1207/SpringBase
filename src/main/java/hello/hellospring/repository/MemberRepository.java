package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MemberForm;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    MemberForm save(MemberForm member);
    Optional<Member> findById(Long id);         //null을 편하게 처리하기 위해 Optional을 이용
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
