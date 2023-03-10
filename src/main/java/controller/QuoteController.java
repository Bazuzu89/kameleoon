package controller;

import DTO.ListOfGraphPointsDTO;
import DTO.ListOfQuotesDTO;
import DTO.QuoteResponseDTO;
import DTO.VotesResponseDTO;
import exceptions.NotFoundException;
import model.Quote;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.QuoteServiceInterface;
import service.VoteServiceInterface;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
public class QuoteController {
    private QuoteServiceInterface quoteService;
    private VoteServiceInterface voteService;

    public QuoteController(QuoteServiceInterface quoteService, VoteServiceInterface voteService) {
        this.quoteService = quoteService;
        this.voteService = voteService;
    }

    @PostMapping("/quotes/create")
    ResponseEntity create(@RequestBody Quote quote) {
        ResponseEntity response;
        try {
            QuoteResponseDTO quoteCreated = quoteService.create(quote);
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
            QuoteResponseDTO quoteUpdated = quoteService.update(id, content);
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
    public ResponseEntity getVotes(@PathVariable long id) {
        ResponseEntity response;
        try {
            VotesResponseDTO votes = voteService.getVotes(id);
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

    @GetMapping("/quotes/{id}")
    public ResponseEntity get(@PathVariable long id) {
        ResponseEntity response;
        try {
            QuoteResponseDTO quote = quoteService.get(id);
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(quote);
        } catch (NotFoundException e) {
            response = ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(String.format("{\"message\": \"%s\"}", e.getMessage()));
        }
        return response;
    }

    @GetMapping("/quotes")
    public ResponseEntity all() {
        ResponseEntity response;
        try {
            List<Quote> quote = quoteService.getAll();
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(quote);
        } catch (NotFoundException e) {
            response = ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(String.format("{\"message\": \"%s\"}", e.getMessage()));
        }
        return response;
    }

    @GetMapping("quotes/topten")
    public ResponseEntity getTopTen() {
        ResponseEntity response;
        try {
//            Page<Quote> quotes = quoteService.getTopTenByField();
            ListOfQuotesDTO quotes = quoteService.getTopTenHQL();
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(quotes);
        } catch (NotFoundException e) {
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(APPLICATION_JSON)
                    .body(String.format("{\"message\": \"%s\"}", e.getMessage()));
        }
        return response;
    }

    @GetMapping("quotes/worstten")
    public ResponseEntity getWorstTen() {
        ResponseEntity response;
        try {
//            Page<Quote> quotes = quoteService.getWorstTenByField();
            ListOfQuotesDTO quotes = quoteService.getWorstTenHQL();
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(quotes);
        } catch (NotFoundException e) {
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(APPLICATION_JSON)
                    .body(String.format("{\"message\": \"%s\"}", e.getMessage()));
        }
        return response;
    }

    @GetMapping("/quotes/votinggraph/{id}")
    public ResponseEntity getGraph(@PathVariable long id) {
        ResponseEntity response;
        try {
            ListOfGraphPointsDTO graph = quoteService.getGraph(id);
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(APPLICATION_JSON)
                    .body(graph);
        } catch (NotFoundException e) {
            response = ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(APPLICATION_JSON)
                    .body(String.format("{\"message\": \"%s\"}", e.getMessage()));
        }
        return response;
    }

    @GetMapping("/quotes/random")
    public ResponseEntity getRandomQuote() {
        ResponseEntity response;
        try {
            QuoteResponseDTO quote = quoteService.getRandomQuote();
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(APPLICATION_JSON)
                    .body(quote);
        } catch(NotFoundException e) {
            response = ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(APPLICATION_JSON)
                    .body(String.format("{\"message\": \"%s\"}", e.getMessage()));
        }
        return response;
    }

}
