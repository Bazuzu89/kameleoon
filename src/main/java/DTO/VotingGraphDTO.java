package DTO;

import org.springframework.hateoas.Link;

import java.util.*;

public interface VotingGraphDTO {

    int getRatingPerDay();
    Date getDate();
}
