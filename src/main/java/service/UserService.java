package service;

import exceptions.NotFoundException;
import exceptions.NotUniqueException;
import model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import repository.UserRepository;
import utils.Validator;

import java.util.Date;
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
    public User create(User user) throws NotUniqueException {
        if (validator.isUniqueUserEmail(user.getEmail())) {
            user.setDateOfCreation(new Date());
            return userRepository.save(user);
        } else {
          throw new NotUniqueException(String.format("Email %s already used", user.getEmail()));
        }
    }

    @Override
    public User get(long id) throws NotFoundException {
        try {
            return userRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException(String.format("User with id %d not found", id));
        }
    }
}
