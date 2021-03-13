package kz.iitu.edu.booking.service.impl;

import kz.iitu.edu.booking.model.User;
import kz.iitu.edu.booking.repository.UserRepository;
import kz.iitu.edu.booking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository repository;

    @Override
    public void create(User user) {
        repository.save(user);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.getAllBy();
    }

    @Override
    public User getUserById(Integer id) {
        return repository.getUserById(id);
    }
}
