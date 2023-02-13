package model;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "quotes")
public class Quote {
    private String content;
    private Date dateOfCreation;
    private Date dateOflastUpdate;

    private long userId;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    //TODO add votes


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Date getDateOflastUpdate() {
        return dateOflastUpdate;
    }

    public void setDateOflastUpdate(Date dateOflastUpdate) {
        this.dateOflastUpdate = dateOflastUpdate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
