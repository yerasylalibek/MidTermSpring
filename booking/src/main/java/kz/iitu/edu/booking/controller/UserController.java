package kz.iitu.edu.booking.controller;

import kz.iitu.edu.booking.model.User;
import kz.iitu.edu.booking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    IUserService service;

    public void createUser(User user){
        service.create(user);
    }

    public void deleteUser(Integer id){
        service.delete(id);
    }

    public User getUserById(Integer id){
        return service.getUserById(id);
    }

    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

}
