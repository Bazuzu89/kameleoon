package DTO;

import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserResponseDTO {
    private String name;
    private String email;
    private Date dateOfCreation;
    private long id;

    private List<Link> links = new ArrayList<>();


    public UserResponseDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(Link link) {
        links.add(link);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
