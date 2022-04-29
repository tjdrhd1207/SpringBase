package hello.hellospring.api;

import hello.hellospring.domain.Address;
import hello.hellospring.domain.Order;
import hello.hellospring.domain.OrderItem;
import hello.hellospring.domain.OrderStatus;
import hello.hellospring.repository.OrderJpaRepository;
import hello.hellospring.repository.OrderSearch;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2(){
        List<Order> orders = orderRepository.findAllByCriteria(new OrderSearch());
        List<OrderDto> collect = orders.stream()
                .map(o->new OrderDto(o))
                .collect(Collectors.toList());

        return collect;
    }

    @Getter
    static class OrderDto{

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItem> orderItems; //DTO안에 엔티티가 들어가면안됨


        public OrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
            order.getOrderItems().stream().forEach(o -> o.getItem().getName());
            orderItems = order.getOrderItems();
        }
    }

}
