package pl.dabal.selfstorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.dabal.selfstorage.model.Storage;
import pl.dabal.selfstorage.model.User;

import java.util.Optional;

@Repository
@Transactional
public interface StorageRepository extends JpaRepository<Storage, Long> {
    Optional<Storage> findByIdAndUser(Long id, User user);
}