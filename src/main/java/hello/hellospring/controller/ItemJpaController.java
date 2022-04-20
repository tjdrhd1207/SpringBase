package hello.hellospring.controller;

import hello.hellospring.domain.item.Book;
import hello.hellospring.domain.item.Item;
import hello.hellospring.service.ItemJpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemJpaController {

    private final ItemJpaService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model){

        model.addAttribute("form", new BookForm());

        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form){


/*
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
*/




        //static 메소드 활용
        itemService.saveItem(Book.createNewBook(form.getId(),
                                                form.getName(),
                                                form.getPrice(),
                                                form.getStockQuantity(),
                                                form.getAuthor(),
                                                form.getIsbn()));

        return "redirect:/";
    }

    @GetMapping("/items")
    public String List(Model model){
        List<Item> items = itemService.findItem();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){
            Book item = (Book) itemService.findItemOne(itemId);

            model.addAttribute("form",BookForm.editBook(item.getId(),
                                                                    item.getName(),
                                                                    item.getPrice(),
                                                                    item.getStockQuantity(),
                                                                    item.getAuthor(),
                                                                    item.getIsbn()));

            return "items/updateItemForm";

    }


    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") BookForm form) {


        //준영속성 상태 객체 : 임의로 만들어낸 엔티티도 기존 식별자를 가지고 있으면 준영속 엔티티로 볼 수 있다.
        //1차 리팩토링

        /*
        itemService.saveItem(Book.createNewBook(form.getId(),
                                                form.getName(),
                                                form.getPrice(),
                                                form.getStockQuantity(),
                                                form.getAuthor(),
                                                form.getIsbn()));
        */

        //2차 리팩토링
        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());


        return "redirect:/items";
    }

}
