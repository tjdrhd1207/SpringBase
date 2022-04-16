package hello.hellospring.service;

import hello.hellospring.domain.Address;
import hello.hellospring.domain.Member;
import hello.hellospring.domain.Order;
import hello.hellospring.domain.OrderStatus;
import hello.hellospring.domain.item.Book;
import hello.hellospring.domain.item.Item;
import hello.hellospring.exception.NotEnoughStockException;
import hello.hellospring.repository.OrderJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderJpaServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderJpaService orderService;
    @Autowired
    OrderJpaRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = createMember();

        Book book = createBook("노인과바다",10000, 100);

        //System.out.println("책 : "+book.getName());

        int orderCount = 8;
        //when

        Long orderId = orderService.order(member.getId(),book.getId(),orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.",1,getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.",10000 * orderCount, getOrder.getTotalPrice() );
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 92, book.getStockQuantity());
    }


    @Test
    public void 주문취소() throws Exception{
        //given
        Member member = createMember();
        Item book = createBook("노인과바다",10000,100);

        int orderCount = 8;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //when

        orderService.cancelOrder(orderId);
        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("주문취소 시 상태는 CANCEL이다.",OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("주문이 취소됐을 때 재고가 다시 늘어나야한다.", 100, book.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws  Exception {
        //given
        Member member = createMember();
        Item book = createBook("노인과바다",10000, 100);

        int orderCOunt = 103;

        //when
        orderService.order(member.getId(), book.getId(), orderCOunt);

        //then
        fail("재고 수량 예외가 발생해야 한다.");

    }


    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book(); //추상(abstract)클래스는 인스턴스 생성 불가
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울시","관악구","213412"));
        em.persist(member);
        return member;
    }


}