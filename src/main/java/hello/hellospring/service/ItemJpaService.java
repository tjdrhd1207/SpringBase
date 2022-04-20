package hello.hellospring.service;

import hello.hellospring.domain.item.Book;
import hello.hellospring.domain.item.Item;
import hello.hellospring.repository.ItemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemJpaService {

    private final ItemJpaRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);

        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);

    }


    public List<Item> findItem(){
        return itemRepository.findAll();
    }

    public Item findItemOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
