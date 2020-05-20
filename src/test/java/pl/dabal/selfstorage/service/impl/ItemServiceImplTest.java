package pl.dabal.selfstorage.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.dabal.selfstorage.model.Item;
import pl.dabal.selfstorage.repository.ItemRepository;
import pl.dabal.selfstorage.service.ItemService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

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


}