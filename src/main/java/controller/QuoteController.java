package controller;

import DTO.VotesDTO;
import exceptions.NotFoundException;
import model.Quote;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.QuoteServiceInterface;
import service.VoteServiceInterface;

@RestController
public class QuoteController {
    private QuoteServiceInterface service;
    private VoteServiceInterface voteService;

    public QuoteController(QuoteServiceInterface service, VoteServiceInterface voteService) {
        this.service = service;
        this.voteService = voteService;
    }

    @PostMapping("/quotes/create")
    ResponseEntity create(@RequestBody Quote quote) {
        ResponseEntity response;
        try {
            Quote quoteCreated = service.create(quote);
            response = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(quoteCreated);
        } catch (NotFoundException e) {
            response = ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(String.format("{\"message\": \"%s\"}", e.getMessage()));
        }
        return response;
    }

    @PutMapping("/quotes/update/{id}")
    ResponseEntity update(@PathVariable long id, @RequestBody String content) {
        ResponseEntity response;
        try {
            Quote quoteUpdated = service.update(id, content);
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(quoteUpdated);
        } catch (NotFoundException e) {
            response = ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(String.format("{\"message\": \"%s\"}", e.getMessage()));
        }
        return response;
    }


    @GetMapping("/quotes/votes/{id}")
    ResponseEntity getVotes(@PathVariable long id) {
        ResponseEntity response;
        try {
            VotesDTO votes = voteService.getVotes(id);
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(votes);
        } catch (NotFoundException e) {
            response = ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(String.format("{\"message\": \"%s\"}", e.getMessage()));
        }
        return response;
    }
}
