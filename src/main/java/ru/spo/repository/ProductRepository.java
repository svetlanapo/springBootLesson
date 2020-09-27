package ru.spo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.spo.domain.Product;


public interface ProductRepository extends JpaRepository<Product, Long>  {
Page<Product> getProductByPriceBetween(double minPrice, double maxPrice, Pageable pageable);
Product getById(Long id);
Page<Product> findAll(Pageable pageable);
}
