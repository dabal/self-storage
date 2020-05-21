package pl.dabal.selfstorage.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.dabal.selfstorage.exception.FormFraudException;
import pl.dabal.selfstorage.model.Item;
import pl.dabal.selfstorage.model.Storage;
import pl.dabal.selfstorage.model.User;
import pl.dabal.selfstorage.model.dto.ItemDto;
import pl.dabal.selfstorage.repository.ItemRepository;
import pl.dabal.selfstorage.service.ItemService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemServiceImpl itemService;

    @Test
    public void getItemForId_shouldReturnEmptyOptionalForMissingId() {
        when(itemRepository.findById(anyLong()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertFalse(itemService.getItemForId(1L).isPresent());
    }

    @Test
    public void getItemForId_shouldReturnNotEmptyOptionalForValidId() {
        when(itemRepository.findById(anyLong()))
                .thenReturn(Optional.ofNullable(new Item()));
        Assertions.assertTrue(itemService.getItemForId(1L).isPresent());
    }

    @Test
    public void saveItemFromDtoForUser_shouldSaveValidItemFromDtoWithoutId() throws FormFraudException {
        when(itemRepository.save(any(Item.class))).thenAnswer(i -> i.getArguments()[0]);
        User user = new User();
        Storage storage = Storage.builder().user(user).build();
        ItemDto itemDto = ItemDto.builder().id(null).storage(storage).build();
        itemService.saveItemFromDtoForUser(itemDto, user);
        verify(itemRepository, times(1)).save(any(Item.class));

    }

    @Test
    public void saveItemFromDtoForUser_shouldSaveValidItemFromDtoWithValidId() throws FormFraudException {
        when(itemRepository.save(any(Item.class))).thenAnswer(i -> i.getArguments()[0]);
        when(itemRepository.findById(anyLong())).thenReturn(Optional.ofNullable(new Item()));
        User user = new User();
        Storage storage = Storage.builder().user(user).build();
        ItemDto itemDto = ItemDto.builder().id(1L).storage(storage).build();
        itemService.saveItemFromDtoForUser(itemDto, user);
        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    public void saveItemFromDtoForUser_shouldThrowExceptionWhenAddingItemToOtherThanLoggedInStorageUser() throws FormFraudException {
        User user = new User();
        User user1 = User.builder().id(1L).build();
        Storage storage = Storage.builder().user(user1).build();
        ItemDto itemDto = ItemDto.builder().id(1L).storage(storage).build();
        Assertions.assertThrows(FormFraudException.class, () -> itemService.saveItemFromDtoForUser(itemDto, user));
    }

    @Test
    public void saveItemFromDtoForUser_shouldThrowExceptionWhenAddingItemWithIdDoesNotExist() throws FormFraudException {
        when(itemRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        User user = new User();
        Storage storage = Storage.builder().user(user).build();
        ItemDto itemDto = ItemDto.builder().id(1L).storage(storage).build();
        Assertions.assertThrows(FormFraudException.class, () -> itemService.saveItemFromDtoForUser(itemDto, user));
    }

}