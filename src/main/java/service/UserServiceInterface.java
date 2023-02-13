package service;

import exceptions.NotFoundException;
import exceptions.NotUniqueException;
import model.User;

public interface UserServiceInterface {
    User create(User user) throws NotUniqueException;

    User get(long id) throws NotFoundException;
}
