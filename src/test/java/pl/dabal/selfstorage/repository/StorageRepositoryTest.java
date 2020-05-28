package pl.dabal.selfstorage.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.dabal.selfstorage.model.Storage;
import pl.dabal.selfstorage.model.User;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StorageRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StorageRepository storageRepository;

    User user1;
    User user2;
    Storage storage;

    @BeforeEach
    public void setUp() {
        user1 = User.builder().username("user1").build();
        user2 = User.builder().username("user2").build();

        entityManager.persist(user1);
        entityManager.persist(user2);
        storage = Storage.builder().user(user1).name("test").build();
        entityManager.persist(storage);
    }

    @Test
    public void getStorageByIdAndUser_shouldReturnRepositoryWhenStorageWithGivenIdBelongsToUser() {
        Storage storage1 = storageRepository.findByIdAndUser(storage.getId(), user1).get();
        Assertions.assertEquals(storage1.getUser(), user1);
    }

    @Test
    public void getStorageByIdAndUser_shouldReturnEmptyOptionalWhenStorageWithGivenIdDoesntBelongsToUser() {
        Assertions.assertTrue(!storageRepository.findByIdAndUser(storage.getId(), user2).isPresent());
    }

    @Test
    public void getStorageByIdAndUser_shouldReturnEmptyOptionalWhenStorageWithGivenIdDoesntExist() {
        Assertions.assertTrue(!storageRepository.findByIdAndUser(-10L, user1).isPresent());
    }

}