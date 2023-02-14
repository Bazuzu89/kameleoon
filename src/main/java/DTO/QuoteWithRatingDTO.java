package DTO;

import jakarta.persistence.Entity;


public interface QuoteWithRatingDTO {

    String getContent();

    long getQuoteId();

    int getRating();

}
