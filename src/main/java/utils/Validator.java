package utils;

import exceptions.NotUniqueException;
import org.springframework.stereotype.Component;
import repository.UserRepository;
import repository.VoteRepository;

@Component
public class Validator {
    private UserRepository userRepository;
    private VoteRepository voteRepository;

    public Validator(UserRepository userRepository, VoteRepository voteRepository) {
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    public boolean isUniqueUserEmail(String email) {
        if(userRepository.findByEmail(email) != null) {
            return false;
        }
        return true;
    }

    public void checkUserVoteQuote(long quoteId, long userId) throws NotUniqueException {
        if (voteRepository.findVoteByQuoteIdAndUserId(quoteId, userId) != null) {
            throw new NotUniqueException(String.format("User %d already voted for quote %d", userId, quoteId));
        }
    }
}
