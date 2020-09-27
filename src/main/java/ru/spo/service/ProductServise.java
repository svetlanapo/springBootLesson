package ru.spo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spo.domain.Product;
import ru.spo.repository.ProductRepository;

@Service
@Transactional
public class ProductServise{


    @Autowired
    private ProductRepository productRepo;

    /*public List<Product> getAll(){
        return productRepo.findAll();
    }
*/
    public Page<Product> getAll(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    public Product save(Product product){
        productRepo.save(product);
        return product;
    }
    public Product getById(Long id){
        return productRepo.getById(id);
    }

    public void delete(Long id){
        productRepo.deleteById(id);
    }


    public Page<Product> getFilterByPrice(double minPrice, double maxPrice, int pageNumber, int pageSize) {
        return productRepo.getProductByPriceBetween(minPrice, maxPrice, PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC,"name"));
    }

    public Page<Product> findPaginated(int pageNumber, int pageSize){
       return productRepo.findAll(PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "name"));

    }

}
