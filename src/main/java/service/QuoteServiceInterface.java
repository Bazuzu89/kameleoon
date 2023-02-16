package service;

import DTO.ListOfGraphPointsDTO;
import DTO.ListOfQuotesDTO;
import DTO.QuoteResponseDTO;
import exceptions.NotFoundException;
import model.Quote;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuoteServiceInterface {
    QuoteResponseDTO create(Quote quote) throws NotFoundException;
    QuoteResponseDTO get(long id) throws NotFoundException;

    QuoteResponseDTO update(long id, String content) throws NotFoundException;

    List<Quote> getAll() throws NotFoundException;

    Page<Quote> getTopTenByField() throws NotFoundException;
    Page<Quote> getWorstTenByField() throws NotFoundException;

    ListOfQuotesDTO getTopTenHQL() throws NotFoundException;

    ListOfQuotesDTO getWorstTenHQL() throws NotFoundException;

    ListOfGraphPointsDTO getGraph(long id) throws NotFoundException;

    QuoteResponseDTO getRandomQuote() throws NotFoundException;
}
