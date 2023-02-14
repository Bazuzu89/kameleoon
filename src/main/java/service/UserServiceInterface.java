package service;

import exceptions.NotFoundException;
import exceptions.NotUniqueException;
import model.Quote;
import model.User;

import java.util.List;

public interface UserServiceInterface {
    User create(User user) throws NotUniqueException;

    User get(long id) throws NotFoundException;

    List<User> getAll() throws NotFoundException;

}
