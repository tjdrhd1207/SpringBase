package hello.hellospring.domain;

import hello.hellospring.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column (name =  "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문 가격
    private int count;      //주문 수량


    protected OrderItem(){

    }   //서비스단에서 OrderItem orderItem1 = new OrderItem();과 같은 생성자를 통해 생성하여 set을 통한 주입을 막고 일관성을 유지시키기 위해 protected로 기본생성자를 보호
        //@NoArgsConstructor(access = AccessLevel.PROTECTER)와 같은 역할    
    
    
    //==생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);

        return orderItem;
    }


    //=비즈니스 로직=//
    public void cancel() {
       getItem().addStock(count);
    }

    //==조회 로직==//
    /*
    *주문상품 전체 가격 조회
    * */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
