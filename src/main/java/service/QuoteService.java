package service;

import exceptions.NotFoundException;
import model.Quote;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import repository.QuoteRepository;
import repository.UserRepository;

import java.util.Date;

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
            quote.setDateOflastUpdate(dateOfCreation);
            return repository.save(quote);
        } else {
            throw new NotFoundException(String.format("User with id %d not found in DB", quote.getUserId()));
        }
    }

    @Override
    public Quote get(long id) {
        return repository.findById(id).get();
    }

    @Override
    public Quote update(long id, String content) throws NotFoundException{
        if (repository.existsById(id)) {
            Quote quote = new Quote();
            quote.setContent(content);
            quote.setId(id);
            quote.setDateOflastUpdate(new Date());
            return repository.save(quote);
        } else {
            throw new NotFoundException(String.format("Quote with id %d not found in DB", id));
        }
    }
}
