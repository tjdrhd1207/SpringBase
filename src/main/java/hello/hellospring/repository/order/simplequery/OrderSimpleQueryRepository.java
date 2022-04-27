package hello.hellospring.repository.order.simplequery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {   //Repository를 분리한 이유 -> Repository는 가급적 순수한 Entity를 조회하기 위해서 씀

    private final EntityManager em;

    public List<OrderSimpleQueryDto> findOrderDto(){
        return em.createQuery("select new hello.hellospring.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                " from Order o" +
                " join o.member m" +
                " join o.delivery d", OrderSimpleQueryDto.class
        ).getResultList();
    }
}
