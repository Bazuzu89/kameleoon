package service;

import DTO.UserResponseDTO;
import DTO.assembler.AssemblerUserResponseDTO;
import exceptions.NotFoundException;
import exceptions.NotUniqueException;
import model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import repository.UserRepository;
import utils.Validator;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService implements UserServiceInterface {
    @Qualifier("userRepository")
    private final UserRepository userRepository;

    private final Validator validator;

    public UserService(UserRepository userRepository, Validator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public UserResponseDTO create(User user) throws NotUniqueException {
        if (validator.isUniqueUserEmail(user.getEmail())) {
            user.setDateOfCreation(new Date());
            User userSaved = userRepository.save(user);
            UserResponseDTO userResponseDTO = AssemblerUserResponseDTO.createUserResponseDTO(userSaved);
            return userResponseDTO;
        } else {
          throw new NotUniqueException(String.format("Email %s already used", user.getEmail()));
        }
    }

    @Override
    public UserResponseDTO get(long id) throws NotFoundException {
        try {
            User user = userRepository.findById(id).get();
            UserResponseDTO userResponseDTO = AssemblerUserResponseDTO.createUserResponseDTO(user);
            return userResponseDTO;
        } catch (NoSuchElementException e) {
            throw new NotFoundException(String.format("User with id %d not found", id));
        }
    }

    @Override
    public List<User> getAll() throws NotFoundException {
        List<User> list = userRepository.findAll();
        if (!list.isEmpty()) {
            return list;
        } else {
            throw new NotFoundException("Users not found");
        }
    }
}
