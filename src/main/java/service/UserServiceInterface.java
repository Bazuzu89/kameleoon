package service;

import DTO.UserResponseDTO;
import exceptions.NotFoundException;
import exceptions.NotUniqueException;
import exceptions.NotValidEmailException;
import model.User;

import java.util.List;

public interface UserServiceInterface {
    UserResponseDTO create(User user) throws NotUniqueException, NotValidEmailException;

    UserResponseDTO get(long id) throws NotFoundException;

    List<User> getAll() throws NotFoundException;

}
