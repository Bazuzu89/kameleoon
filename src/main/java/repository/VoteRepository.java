package repository;

import DTO.VotingGraphDTO;
import model.Vote;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Qualifier("voteRepository")
public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findAllByQuoteId(long quoteId);

    @Query("SELECT COUNT (*) FROM Vote votes WHERE votes.quoteId = :quoteId AND votes.vote = 1")
    int getUpVotesSumByQuoteId(@Param("quoteId") long quoteId);

    @Query("SELECT COUNT (*) FROM Vote votes WHERE votes.quoteId = :quoteId AND votes.vote = -1")
    int getDownVotesSumByQuoteId(@Param("quoteId") long quoteId);


    Vote findVoteByQuoteIdAndUserId(long quoteId, long userId);

    @Query("SELECT votes.dateOfVote AS date, SUM(votes.vote) AS ratingPerDay FROM Vote votes " +
            "WHERE votes.quoteId = :idquote GROUP BY date")
    List<VotingGraphDTO> getGraph(@Param("idquote") long id);
}
