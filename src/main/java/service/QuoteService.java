package service;

import DTO.*;
import DTO.assembler.AssemblerListOfGraphPointsDTO;
import DTO.assembler.AssemblerListOfQuotesDTO;
import DTO.assembler.AssemblerQuoteResponseDTO;
import exceptions.NotFoundException;
import model.Quote;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.QuoteRepository;
import repository.UserRepository;
import repository.VoteRepository;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class QuoteService implements QuoteServiceInterface{
    @Qualifier("quoteRepository")
    private QuoteRepository repository;
    private UserRepository userRepository;
    private VoteRepository voteRepository;

    public QuoteService(QuoteRepository repository, UserRepository userRepository, VoteRepository voteRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    public QuoteResponseDTO create(Quote quote) throws NotFoundException {
        if (userRepository.existsById(quote.getUserId())) {
            Date dateOfCreation = new Date();
            quote.setDateOfCreation(dateOfCreation);
            quote.setDateOfLastUpdate(dateOfCreation);
            Quote quoteSaved = repository.save(quote);
            QuoteResponseDTO quoteResponseDTO = AssemblerQuoteResponseDTO.createQuoteResponseDTO(quoteSaved);
            return quoteResponseDTO;
        } else {
            throw new NotFoundException(String.format("User with id %d not found in DB", quote.getUserId()));
        }
    }

    @Override
    public QuoteResponseDTO get(long id) throws NotFoundException {
        try {
            Quote quote = repository.findById(id).get();
            QuoteResponseDTO quoteResponseDTO = AssemblerQuoteResponseDTO.createQuoteResponseDTO(quote);
            return quoteResponseDTO;
        } catch (NoSuchElementException e) {
            throw new NotFoundException(String.format("Quote %d not found in DB", id));
        }
    }

    @Override
    public QuoteResponseDTO update(long id, String content) throws NotFoundException{
        if (repository.existsById(id)) {
            Quote quote = new Quote();
            quote.setContent(content);
            quote.setId(id);
            quote.setDateOfLastUpdate(new Date());
            Quote quoteSaved = repository.save(quote);
            QuoteResponseDTO quoteResponseDTO = AssemblerQuoteResponseDTO.createQuoteResponseDTO(quote);
            return quoteResponseDTO;
        } else {
            throw new NotFoundException(String.format("Quote with id %d not found in DB", id));
        }
    }

    @Override
    public List<Quote> getAll() throws NotFoundException {
        List<Quote> list = repository.findAll();
        if (!list.isEmpty()) {
            return list;
        } else {
            throw new NotFoundException("Quotes not found");
        }
    }

    @Override
    public Page<Quote> getTopTenByField() throws NotFoundException {
        PageRequest pageable = PageRequest.of(0,10, Sort.Direction.DESC, "votes");
        Page<Quote> quotes = repository.findAll(pageable);
        if (!quotes.isEmpty()) {
            return quotes;
        } else {
            throw new NotFoundException("Quotes not found");
        }
    }

    @Override
    public Page<Quote> getWorstTenByField() throws NotFoundException {
        PageRequest pageable = PageRequest.of(0,10, Sort.Direction.ASC, "votes");
        Page<Quote> quotes = repository.findAll(pageable);
        if (!quotes.isEmpty()) {
            return quotes;
        } else {
            throw new NotFoundException("Quotes not found");
        }
    }

    @Override
    public ListOfQuotesDTO getTopTenHQL() throws NotFoundException {
        PageRequest pageable = PageRequest.of(0,10, Sort.Direction.DESC, "rating");
        Page<QuoteWithRatingDTO> quotes = repository.getTen(pageable);
        if (!quotes.isEmpty()) {
            List<QuoteWithRatingDTO> listOfQuotes = quotes.getContent();
            ListOfQuotesDTO listOfQuotesDTO = AssemblerListOfQuotesDTO.createListOfQuotesDTO(listOfQuotes);
            return listOfQuotesDTO;
        } else {
            throw new NotFoundException("Quotes not found");
        }
    }

    @Override
    public ListOfQuotesDTO getWorstTenHQL() throws NotFoundException {
        PageRequest pageable = PageRequest.of(0,10, Sort.Direction.ASC, "rating");
        Page<QuoteWithRatingDTO> quotes = repository.getTen(pageable);
        if (!quotes.isEmpty()) {
            List<QuoteWithRatingDTO> listOfQuotes = quotes.getContent();
            ListOfQuotesDTO listOfQuotesDTO = AssemblerListOfQuotesDTO.createListOfQuotesDTO(listOfQuotes);
            return listOfQuotesDTO;
        } else {
            throw new NotFoundException("Quotes not found");
        }
    }

    @Override
    public ListOfGraphPointsDTO getGraph(long id) throws NotFoundException {
        List<VotingGraphDTO> graph = voteRepository.getGraph(id);
        if (!graph.isEmpty()) {
            ListOfGraphPointsDTO listOfGraphPointsDTO = AssemblerListOfGraphPointsDTO.createListOfGraphPointsDTO(graph, id);
            return listOfGraphPointsDTO;
        } else {
            throw new NotFoundException("Votes not found");
        }
    }

}
