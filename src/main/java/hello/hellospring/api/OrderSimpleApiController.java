package hello.hellospring.api;

import hello.hellospring.domain.Order;
import hello.hellospring.repository.OrderJpaRepository;
import hello.hellospring.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * xToOne(ManyToOne, OneToOne)
 * API SPEC (Member정보와 Delivery정보만 필요)
 * Order
 * Order -> Member
 * Order -> Delivery
* */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderJpaRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
        for(Order order : all){
            order.getMember().getName();        //Lazy 강제 초기화
            order.getDelivery().getAddress();   //Lazy 강제 초기화
        }
        return all;
    }

}
