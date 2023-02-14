package DTO.assembler;

import DTO.UserResponseDTO;
import controller.UserController;
import model.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class AssemblerUserResponseDTO {

    public static UserResponseDTO createUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setDateOfCreation(user.getDateOfCreation());
        userResponseDTO.addLink(linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel());
        userResponseDTO.addLink(linkTo(methodOn(UserController.class).all()).withRel("users"));
        userResponseDTO.setId(user.getId());
        return userResponseDTO;
    }
}
