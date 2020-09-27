package ru.spo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.spo.domain.Product;
import ru.spo.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAll(Pageable pageable);

}
