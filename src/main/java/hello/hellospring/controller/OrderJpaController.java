package hello.hellospring.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import hello.hellospring.domain.Member;
import hello.hellospring.domain.Order;
import hello.hellospring.domain.item.Item;
import hello.hellospring.dto.OrderItemsDTO;
import hello.hellospring.service.ItemJpaService;
import hello.hellospring.service.MemberJpaService;
import hello.hellospring.service.OrderJpaService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

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
    public String create(@RequestBody List<OrderItemsDTO> orderItemJson) throws IOException, JSONException {

        ObjectMapper mapper = new ObjectMapper();

        //Map<String, String> map = mapper.readValue(orderItemJson, Map.class);
        System.out.println("제이슨 : "+orderItemJson.toString());

        for(int i=0; i<orderItemJson.size(); i++) {
            orderService.order(orderItemJson.get(i).getMemberId(), orderItemJson.get(i).getItemId(), orderItemJson.get(i).getCount());
        }
  /*      JSONObject jsonObject = new JSONObject(orderItemJson);
        JSONArray jsonArray = jsonObject.getJSONArray("");


        for(int i=0; i<jsonObject.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            Long memberId = obj.getLong("memberId");
            Long itemId = obj.getLong("itemId");
            int count = obj.getInt("count");

            System.out.println(memberId+", "+itemId+", "+count);
        }*/

        //System.out.println("맵 : "+orderItemJson+",  ");

        return "redirect:/";
    }


}
