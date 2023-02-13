package service;

import DTO.VotesDTO;
import exceptions.NotFoundException;
import exceptions.NotUniqueException;
import model.Vote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoteServiceInterface {
    Vote create(Vote vote) throws NotFoundException, NotUniqueException;
    List<Vote> getAllByQuoteId(long quoteId);
    Vote get(long id);

    VotesDTO getVotes(long quoteId) throws NotFoundException;
}
