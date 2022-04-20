package hello.hellospring.controller;

import com.fasterxml.jackson.core.JsonParser;
import hello.hellospring.domain.Member;
import hello.hellospring.domain.item.Item;
import hello.hellospring.dto.OrderItemsDTO;
import hello.hellospring.service.ItemJpaService;
import hello.hellospring.service.MemberJpaService;
import hello.hellospring.service.OrderJpaService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class OrderJpaController {

    private final OrderJpaService orderService;
    private final MemberJpaService memberService;
    private final ItemJpaService itemService;

    @GetMapping("/order")
    public String createForm(Model  model){

        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItem();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }
    
    /* 원본
    @PostMapping("/order")  //현재 하나의 상품만 주문됨
    public String create(@RequestParam("memberId") Long memberId,
                         @RequestParam("itemId") Long itemId,
                         @RequestParam("count") int count){

        System.out.println("멤버id : "+memberId+", 아이템id : "+itemId+", 카운트 : "+count);

        orderService.order(memberId, itemId, count);
        return "redirect:/order";
    }
    */

    @ResponseBody
    @PostMapping("/order")  //현재 하나의 상품만 주문됨
    public String create(@RequestBody  HashMap<String, Object> map){

         System.out.println("맵 : "+map);

//       System.out.println("멤버id : " + orderItemsDTO.getMemberId() + ", 아이템id : " + orderItemsDTO.getItemId() + ", 카운트 : " + orderItemsDTO.getCount());

/*
            for (OrderItemsDTO od : orderItemsDTO.getOrderItemsDTOs()){
                System.out.println("배열 멤버id : "+od.getMemberId());
                System.out.println("배열 아이템id : "+od.getItemId());
                System.out.println("개수 : "+od.getCount());
            }
*/

        //orderService.order(memberId, itemId, count);
        return "redirect:/order";
    }


}
