package hello.hellospring.api;

import hello.hellospring.domain.Order;
import hello.hellospring.domain.OrderItem;
import hello.hellospring.repository.OrderJpaRepository;
import hello.hellospring.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderJpaRepository orderRepository;

    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
        for(Order order : all){
           order.getMember().getName();
           order.getDelivery().getAddress();
           List<OrderItem> orderItems = order.getOrderItems();
           /*
           for(OrderItem orderItem : orderItems){
               orderItem.getItem().getName();
           }
           */
            orderItems.stream().forEach(o -> o.getItem().getName());    //프록시 강제 초기화
        }
        return all;
    }
}
