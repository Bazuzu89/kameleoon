package model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private long userId;
    private long quoteId;
    private int vote;

    private LocalDate dateOfVote;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(long quoteId) {
        this.quoteId = quoteId;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public LocalDate getDateOfVote() {
        return dateOfVote;
    }

    public void setDateOfVote(LocalDate dateOfVote) {
        this.dateOfVote = dateOfVote;
    }
}
