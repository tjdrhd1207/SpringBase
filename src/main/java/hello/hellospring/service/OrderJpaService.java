package hello.hellospring.service;

import hello.hellospring.domain.*;
import hello.hellospring.domain.item.Item;
import hello.hellospring.repository.ItemJpaRepository;
import hello.hellospring.repository.MemberJpaRepository;
import hello.hellospring.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderJpaService {

    private final OrderJpaRepository orderRepository;
    private final MemberJpaRepository memberRepository;
    private final ItemJpaRepository itemRepository;

    /**
     * 주문
     * */
    @Transactional
    public Long order(Long memberId, Long itemId,int count){


        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);
        
        //주문 저장
        orderRepository.save(order);    //Order도메인에서 OrderItem Column, delivery에 Cascade를 걸어줬기 때문에 save를 order만 해도 됨,
                                        //cascade 하지 않았다면, delivery또한 save해야되고 Orderitem도 save해야됨,
                                        //이런 식으로 개발해도되는 경우 -> Order가 Delivery를 관리하고, Order가 OrderItem을 관리하는데 참조하는 경우가 주인이 private하게
                                        //참조할 때만 사용 가능
        return order.getId();
    }


    //취소
    /**
     * 주문 취소
     * */
    @Transactional
    public void cancelOrder(Long orderId){
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();
    }

    //검색
    /*
        public List<Order> findOrders(orderSearch orderSearch){
            return orderRepository.findAll(orderSearch);
        }
    */
}
