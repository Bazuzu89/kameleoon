package DTO;

import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.List;

public class ListOfQuotesDTO {
    List<QuoteWithRatingDTO> listOfDTO = new ArrayList<>();

    List<Link> links = new ArrayList<>();

    public List<QuoteWithRatingDTO> getListOfDTO() {
        return listOfDTO;
    }

    public void setListOfDTO(List<QuoteWithRatingDTO> listOfDTO) {
        this.listOfDTO = listOfDTO;
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
}
