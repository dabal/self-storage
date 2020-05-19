package pl.dabal.selfstorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dabal.selfstorage.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}