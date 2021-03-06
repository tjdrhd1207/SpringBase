package hello.hellospring.api;

import hello.hellospring.domain.Address;
import hello.hellospring.domain.Order;
import hello.hellospring.domain.OrderItem;
import hello.hellospring.domain.OrderStatus;
import hello.hellospring.repository.OrderJpaRepository;
import hello.hellospring.repository.OrderSearch;
import hello.hellospring.repository.order.query.OrderQueryDto;
import hello.hellospring.repository.order.query.OrderQueryRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderJpaRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

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
           --> 밑에 람다로 변환 */
            orderItems.stream().forEach(o -> o.getItem().getName());    //프록시 강제 초기화 ( 주문과 관련된 orderItems를 다 가져온 후 돌리면서 아이템명을 가져오면서 초기화)
        }
        return all;
    }

    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2(){
        List<Order> orders = orderRepository.findAllByCriteria(new OrderSearch());
        List<OrderDto> collect = orders.stream()
                .map(o->new OrderDto(o))
                .collect(toList());
        System.out.println(collect.toString());
        return collect;
    }

    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3(){
        List<Order> orders = orderRepository.findAllWithItem();

        for(Order order : orders){
            System.out.println("order ref = "+ order + "id ="+order.getId());
        }

        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> ordersV3_page(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                        @RequestParam(value = "limit", defaultValue = "100") int limit){
        List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);

        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> ordersV4(){
        return orderQueryRepository.findOrderQueryDtos();
    }

    @Getter
    static class OrderDto{

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems; //DTO안에 엔티티가 들어가면안됨

        public OrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
            orderItems = order.getOrderItems().stream().map(OrderItemDto::new)
                    .collect(toList());
        }
    }

    @Getter
    static class OrderItemDto{

        private String itemName;       //상품 명
        private int orderPrice;        //주문 가격
        private int count;             //주문 수량

        public OrderItemDto(OrderItem orderItem) {
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }
    }



}
