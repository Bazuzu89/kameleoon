package repository;

import DTO.QuoteWithRatingDTO;
import model.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("quoteRepository")
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Query("SELECT quotes.content AS content, quotes.id AS quoteId, SUM (votes.vote) AS rating  FROM Quote quotes LEFT JOIN Vote votes ON quotes.id = votes.quoteId GROUP BY quotes.id")
    Page<QuoteWithRatingDTO> getTen(Pageable pageable);

}
