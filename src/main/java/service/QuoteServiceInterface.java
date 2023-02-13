package service;

import exceptions.NotFoundException;
import model.Quote;

public interface QuoteServiceInterface {
    Quote create(Quote quote) throws NotFoundException;
    Quote get(long id);

    Quote update(long id, String content) throws NotFoundException;
}
