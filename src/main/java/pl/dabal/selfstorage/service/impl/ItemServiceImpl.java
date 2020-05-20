package pl.dabal.selfstorage.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dabal.selfstorage.exception.FormFraudException;
import pl.dabal.selfstorage.model.Item;
import pl.dabal.selfstorage.model.Storage;
import pl.dabal.selfstorage.model.User;
import pl.dabal.selfstorage.model.dto.ItemDto;
import pl.dabal.selfstorage.repository.ItemRepository;
import pl.dabal.selfstorage.service.ItemService;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Override
    public ItemDto getItemDtoForItem(Item item) {
        if (item == null) {
            return new ItemDto();
        }
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .quantity(item.getQuantity())
                .category(item.getCategory())
                .metric(item.getMetric())
                .storage(item.getStorage())
                .build();
    }

    @Override
    public ItemDto getItemDtoForItemId(Long id) {
        if (id == null) {
            return new ItemDto();
        }
        Item item = getItemForId(id).orElse(null);
        return getItemDtoForItem(item);
    }

    @Override
    public Optional<Item> getItemForId(Long id) {
        return itemRepository.findById(id);
    }

    private void applyDtoToItem(ItemDto itemDto, Item item) {
        item.setCategory(itemDto.getCategory());
        item.setMetric(itemDto.getMetric());
        item.setName(itemDto.getName());
        item.setQuantity(itemDto.getQuantity());
        item.setStorage(itemDto.getStorage());
    }

    @Override
    public void saveItemFromDtoForUser(ItemDto itemDto, User user) throws FormFraudException {
        if (itemDto.getStorage().getUser() == user) {
            if (itemDto.getId() == null) {
                Item item = new Item();
                applyDtoToItem(itemDto, item);
                itemRepository.save(item);
            } else {
                Item item = itemRepository.findById(itemDto.getId()).orElseThrow(() -> new FormFraudException("fraud someone try to modify form"));
                applyDtoToItem(itemDto, item);
                itemRepository.save(item);
            }
        } else {
            throw new FormFraudException("fraud someone try to modify form");
        }
    }


}

