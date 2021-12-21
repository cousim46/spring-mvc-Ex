package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();
    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA",4500,1);
        //when
        Item save = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());

        Assertions.assertThat(findItem).isEqualTo(save);

    }
    @Test
    void findAll() {
        //given
        Item item1 = new Item("item1",4500,1);
        Item item2 = new Item("item2",9000,10);

        itemRepository.save(item1);
        itemRepository.save(item2);
        List<Item> result = itemRepository.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(item1,item2);

    }
    @Test
    void updateItem() {
        //given
        Item item1 = new Item("item1",4500,1);
        Item save = itemRepository.save(item1);
        Long id = save.getId();
        //when
        Item updateParam = new Item("item2", 9000, 10);
        itemRepository.update(id,updateParam);

        //then
        Item byId = itemRepository.findById(id);
        Assertions.assertThat(byId.getItemName()).isEqualTo(updateParam.getItemName());
        Assertions.assertThat(byId.getQuantity()).isEqualTo(updateParam.getQuantity());
        Assertions.assertThat(byId.getPrice()).isEqualTo(updateParam.getPrice());

    }



}