package service;

import exceptions.NotFoundException;
import model.Quote;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.QuoteRepository;
import repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class QuoteService implements QuoteServiceInterface{
    @Qualifier("quoteRepository")
    private QuoteRepository repository;
    private UserRepository userRepository;

    public QuoteService(QuoteRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public Quote create(Quote quote) throws NotFoundException {
        if (userRepository.existsById(quote.getUserId())) {
            Date dateOfCreation = new Date();
            quote.setDateOfCreation(dateOfCreation);
            quote.setDateOfLastUpdate(dateOfCreation);
            return repository.save(quote);
        } else {
            throw new NotFoundException(String.format("User with id %d not found in DB", quote.getUserId()));
        }
    }

    @Override
    public Quote get(long id) throws NotFoundException {
        try {
            return repository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException(String.format("Quote %d not found in DB", id));
        }
    }

    @Override
    public Quote update(long id, String content) throws NotFoundException{
        if (repository.existsById(id)) {
            Quote quote = new Quote();
            quote.setContent(content);
            quote.setId(id);
            quote.setDateOfLastUpdate(new Date());
            return repository.save(quote);
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
    public Page<Quote> getTopTen() throws NotFoundException {
        PageRequest pageable = PageRequest.of(1,10, Sort.Direction.DESC, "votes");
        Page<Quote> quotes = repository.findAll(pageable);
        if (!quotes.isEmpty()) {
            return quotes;
        } else {
            throw new NotFoundException("Quotes not found");
        }
    }

    public Page<Quote> getWorstTen() throws NotFoundException {
        PageRequest pageable = PageRequest.of(1,10, Sort.Direction.ASC, "votes");
        Page<Quote> quotes = repository.findAll(pageable);
        if (!quotes.isEmpty()) {
            return quotes;
        } else {
            throw new NotFoundException("Quotes not found");
        }
    }
}
