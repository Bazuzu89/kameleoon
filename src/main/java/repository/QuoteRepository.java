package repository;

import model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("quoteRepository")
public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
