package pl.dabal.selfstorage.service;

import pl.dabal.selfstorage.model.Item;
import pl.dabal.selfstorage.model.Storage;
import pl.dabal.selfstorage.model.User;

import java.util.List;
import java.util.Optional;

public interface StorageService {
    public List<Storage> getStorageListForUser(User user);

    Optional<Storage> getStorageByIdAndUser(Long id, User user);

    List<Item> getItemsForStorage(Storage storage);
}
