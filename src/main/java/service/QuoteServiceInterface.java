package service;

import exceptions.NotFoundException;
import model.Quote;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuoteServiceInterface {
    Quote create(Quote quote) throws NotFoundException;
    Quote get(long id) throws NotFoundException;

    Quote update(long id, String content) throws NotFoundException;

    List<Quote> getAll() throws NotFoundException;

    Page<Quote> getTopTen() throws NotFoundException;
    Page<Quote> getWorstTen() throws NotFoundException;
}
