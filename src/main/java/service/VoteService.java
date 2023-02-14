package service;

import DTO.VotesResponseDTO;
import exceptions.NotFoundException;
import exceptions.NotUniqueException;
import jakarta.transaction.Transactional;
import model.Quote;
import model.Vote;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import repository.QuoteRepository;
import repository.UserRepository;
import repository.VoteRepository;
import utils.Validator;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static DTO.assembler.AssemblerVotesDTO.createVotesDTO;

@Service
public class VoteService implements VoteServiceInterface {

    @Qualifier("voteRepository")
    private VoteRepository voteRepository;
    private UserRepository userRepository;
    private QuoteRepository quoteRepository;

    private Validator validator;

    public VoteService(VoteRepository voteRepository, UserRepository userRepository, QuoteRepository quoteRepository, Validator validator) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.quoteRepository = quoteRepository;
        this.validator = validator;
    }

    @Override
    @Transactional
    public Vote create(Vote vote) throws NotFoundException, NotUniqueException {
        if (userRepository.existsById(vote.getUserId())
                && quoteRepository.existsById(vote.getQuoteId())) {
            validator.checkUserVoteQuote(vote.getQuoteId(), vote.getUserId());
            vote.setDateOfVote(new Date());
            Quote quote = quoteRepository.findById(vote.getQuoteId()).get();
            quote.setVotes(quote.getVotes() + vote.getVote());
            quoteRepository.save(quote);
            return voteRepository.save(vote);
        } else {
            throw new NotFoundException(String.format("User %d or quote %d not found in DB", vote.getUserId(), vote.getQuoteId()));
        }
    }

    @Override
    public List<Vote> getAllByQuoteId(long quoteId) {
        return voteRepository.findAllByQuoteId(quoteId);
    }

    @Override
    public Vote get(long id) {
        return voteRepository.findById(id).get();
    }

    @Override
    public VotesResponseDTO getVotes(long quoteId) throws NotFoundException {
        if (quoteRepository.existsById(quoteId)) {
            int upVotes = voteRepository.getUpVotesSumByQuoteId(quoteId);
            int downVotes = voteRepository.getDownVotesSumByQuoteId(quoteId);
            return createVotesDTO(upVotes, downVotes);
        } else {
            throw new NotFoundException(String.format("Quote %d not found", quoteId));
        }
    }
}
