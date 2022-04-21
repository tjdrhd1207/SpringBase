package hello.hellospring.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItemsDTO {

    private Long memberId;
    private Long itemId;
    private int count;

    //Wprivate List<OrderItemsDTO> orderItemsDTOs;

}
