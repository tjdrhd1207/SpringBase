package hello.hellospring.controller;

import hello.hellospring.domain.item.Book;
import hello.hellospring.service.ItemJpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

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
        itemService.saveItem(Book.createNewBook(form.getName(),
                                                form.getPrice(),
                                                form.getStockQuantity(),
                                                form.getAuthor(),
                                                form.getIsbn()));

        return "redirect:/";
    }

}
