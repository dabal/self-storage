package pl.dabal.selfstorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dabal.selfstorage.model.Item;

import javax.transaction.Transactional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
} 