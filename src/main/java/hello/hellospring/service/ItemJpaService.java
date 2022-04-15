package hello.hellospring.service;

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

    public List<Item> findItem(){
        return itemRepository.findAll();
    }

    public Item findItemOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
