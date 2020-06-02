package pl.dabal.selfstorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dabal.selfstorage.model.User;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByUsernameAndEnabled(String username, Integer enabled);

    User findByTokenAndEnabled(String token, Integer enabled);
} 