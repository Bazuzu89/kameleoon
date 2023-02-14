package controller;

import exceptions.NotFoundException;
import exceptions.NotUniqueException;
import jakarta.servlet.http.HttpServletRequest;
import model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserServiceInterface;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class UserController {
    private final UserServiceInterface userService;

    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users/register", consumes = {"application/json"}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody User user, HttpServletRequest request) {
        ResponseEntity response;
        try {
            User userCreated = userService.create(user);
            response = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(userCreated);
        } catch (NotUniqueException e) {
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(String.format("{\"message\": \"%s\"}", e.getMessage()));
        }
        return response;
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity getUser(@PathVariable long id) {
        ResponseEntity response;
        try {
            User user = userService.get(id);
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(user);
        } catch (NotFoundException e) {
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(APPLICATION_JSON)
                    .body(String.format("{\"message\": \"%s\"}", e.getMessage()));
        }
        return response;
    }

    @GetMapping(value = "/users")
    public ResponseEntity all() {
        ResponseEntity response;
        try {
            List<User> user = userService.getAll();
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(user);
        } catch (NotFoundException e) {
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(APPLICATION_JSON)
                    .body(String.format("{\"message\": \"%s\"}", e.getMessage()));
        }
        return response;
    }
}
