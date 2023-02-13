package controller;

import DTO.VotesDTO;
import exceptions.NotFoundException;
import exceptions.NotUniqueException;
import model.Vote;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.VoteServiceInterface;

import java.util.List;
@RestController
public class VoteController {
    private VoteServiceInterface service;

    public VoteController(@Qualifier("voteService") VoteServiceInterface service) {
        this.service = service;
    }

    @PostMapping("/votes/create")
    ResponseEntity create(@RequestBody Vote vote) {
        ResponseEntity response;
        try {
            Vote voteCreated = service.create(vote);
            response = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(voteCreated);
        } catch (NotFoundException e) {
            response = ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(String.format("{\"message\": \"%s\"}", e.getMessage()));
        } catch (NotUniqueException e) {
            response = ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(String.format("{\"message\": \"%s\"}", e.getMessage()));
        }
        return response;
    }

    @GetMapping("/votes/{id}")
    ResponseEntity one(@PathVariable long id) {
        Vote vote = service.get(id);
        ResponseEntity response = ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(vote);
        return response;
    }

    @GetMapping("/votes/quote/{quoteId}")
    ResponseEntity allVotesForQuote(@PathVariable long quoteId) {
        List<Vote> listOfVotes = service.getAllByQuoteId(quoteId);
        ResponseEntity response = ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(listOfVotes);
        return response;
    }

    @GetMapping("/votes/result/{quoteId}")
    ResponseEntity votesResultForQuote(@PathVariable long quoteId) {
        ResponseEntity response;
        try {
            VotesDTO votesDTO = service.getVotes(quoteId);
            response = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(votesDTO);
        } catch (NotFoundException e) {
            response = ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(String.format("{\"message\": \"%s\"}", e.getMessage()));
        }
        return response;
    }
}
