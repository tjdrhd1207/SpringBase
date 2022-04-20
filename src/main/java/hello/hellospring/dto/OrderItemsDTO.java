package hello.hellospring.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderItemsDTO {

    private Long memberId;
    private Long itemId;
    private int count;

    private List<OrderItemsDTO> orderItemsDTOs;

}
