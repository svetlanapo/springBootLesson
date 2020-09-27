package ru.spo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spo.domain.Product;
import ru.spo.domain.User;
import ru.spo.repository.UserRepository;

@Service
@Transactional
public class UserServise {


    @Autowired
    private UserRepository userRepository;

    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User save(User user){
        userRepository.save(user);
        return user;
    }

    public void delete(Long id){

        userRepository.deleteById(id);
    }


    public Page<User> findPaginated(int pageNumber, int pageSize){
       return userRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "firstName"));

    }

    public User getById(long id) {
        return userRepository.getOne(id);
    }
}
