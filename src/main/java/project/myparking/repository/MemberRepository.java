package project.myparking.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.myparking.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    // 저장 logic
    public void save(Member member){
        em.persist(member);     // 영속성 컨텍스트에 member entity 를 주입 ( 그럼 나중에 transaction이 commit 되는 시점에 DB에 반영이 된다. DB에 Insert query 날라감)
    }

    // 단건 조회 logic
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    // 리스트(목록) 조회 logic
    public List<Member> findAll(){
        // createQuery(JPQL, 반환타입) 작성
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 이름으로 회원리스트 조회
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }



}
