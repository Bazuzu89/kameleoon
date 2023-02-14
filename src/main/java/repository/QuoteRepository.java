package repository;

import model.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("quoteRepository")
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Query("SELECT quotes.content, quotes.id, SUM (votes.vote) AS sumvotes  FROM Quote quotes LEFT JOIN Vote votes WHERE quotes.id = votes.quoteId GROUP BY quotes.id")
    Page<Quote> getTen(Pageable pageable);
}
