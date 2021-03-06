package pl.dabal.selfstorage.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dabal.selfstorage.model.Item;
import pl.dabal.selfstorage.model.Storage;
import pl.dabal.selfstorage.model.User;
import pl.dabal.selfstorage.repository.StorageRepository;
import pl.dabal.selfstorage.service.StorageService;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class StorageServiceImpl implements StorageService {

    private StorageRepository storageRepository;

    @Override
    public List<Storage> getStorageListForUser(User user) {
        if (user == null) {
            return null;
        }
        return user.getStorages();
    }

    @Override
    public Optional<Storage> getStorageByIdAndUser(Long id, User user) {
        return storageRepository.findByIdAndUser(id, user);
    }

    @Override
    public List<Item> getItemsForStorage(Storage storage) {
        return storage.getItems();
    }

    @Override
    public Storage createStorage(User user, String name) {
        String propperName = (name == null ? "default" : name);
        Storage storage = Storage.builder().user(user).name(propperName).build();
        return storage;
    }

    @Override
    public void save(Storage storage) {
        storageRepository.save(storage);
    }
}
