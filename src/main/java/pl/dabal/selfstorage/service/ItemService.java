package pl.dabal.selfstorage.service;


import pl.dabal.selfstorage.exception.FormFraudException;
import pl.dabal.selfstorage.model.Item;
import pl.dabal.selfstorage.model.Storage;
import pl.dabal.selfstorage.model.User;
import pl.dabal.selfstorage.model.dto.ItemDto;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    ItemDto getItemDtoForItem(Item item);

    ItemDto getItemDtoForItemId(Long id);

    Optional<Item> getItemForId(Long id);

    void saveItemFromDtoForUser(ItemDto itemDto, User user) throws FormFraudException;


}
